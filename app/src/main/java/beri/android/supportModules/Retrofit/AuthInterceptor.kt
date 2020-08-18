package beri.android.supportModules.Retrofit

import android.content.Context
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.DataService.RefreshResponce
import beri.android.supportModules.Repository.RefreshRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context): Interceptor {

    private val refreshRepository = RefreshRepository(BeriDatabase.getInstance(context).TokenDao())
    private val authorizedDelegate = AuthorizationDelegate.instance

    override fun intercept(chain: Interceptor.Chain): Response? {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Operation-System", "ANDROID")
        requestBuilder.addHeader("Application-Version", "1.1")
        requestBuilder.addHeader("Operation-Version", "13.2")
        refreshRepository.getAccessToken()?.let {
            authorizedDelegate.setData(true)
            requestBuilder.addHeader("Authorization", "Bearer ${refreshRepository.getAccessToken()}")
        }

       // println("1234 ${refreshRepository.getAccessToken()}")

        val initialResponce = chain.proceed(requestBuilder.build())

        when {
            initialResponce.code() == 401 || initialResponce.code() == 403 -> {

                //val refreshToken = refreshRepository.getRefreshToken() ?: throw IllegalArgumentException("token expected")
                val responceNewToken = RetrofitInstance.instance(context).refreshTokens(RefreshResponce(refreshRepository.getRefreshToken())).execute()

                return when {
                    responceNewToken.code() != 200 -> {
                        authorizedDelegate.setData(false)
                        initialResponce
                    }
                    else -> {
                        refreshRepository.updateTokens(responceNewToken.body())
                        //authorizedDelegate.setData(true)
                        val newHeaderRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer ${responceNewToken.body()?.authorization?.token}").build()
                        chain.proceed(newHeaderRequest)
                    }
                }
            }
            else -> return initialResponce
        }
    }

}

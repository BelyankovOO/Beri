package beri.android.supportModules.Retrofit


import android.content.Context
import beri.android.supportModules.ServiceInterface.InterfaceService
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun instance(context: Context): InterfaceService{
            val dispatcher = Dispatcher()
            dispatcher.maxRequests = 1

            val okHttpClient = OkHttpClient.Builder().dispatcher(dispatcher)
                .addInterceptor(
                    AuthInterceptor(
                        context.applicationContext
                    )
                ).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://test.beri.gift/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create<InterfaceService>(InterfaceService::class.java)
        }
    }
}
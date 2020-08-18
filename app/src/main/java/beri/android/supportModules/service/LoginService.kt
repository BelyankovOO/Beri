package beri.android.supportModules.service

import android.util.Log
import beri.android.supportModules.DataService.*
import beri.android.supportModules.ServiceInterface.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(private val interfaceService: InterfaceService){

    fun getMessage(response: IdentifyResponse, callBack: (IdentifyRequest?) -> Unit) {
        val Call = interfaceService.getMessage(response)

        Call.enqueue(object : Callback<IdentifyRequest> {
            override fun onFailure(call: Call<IdentifyRequest>?, t: Throwable?) {
                Log.d("LoginService.OnFailure", t!!.message)
                callBack(null)
            }

            override fun onResponse(call: Call<IdentifyRequest>?, response: Response<IdentifyRequest>?) {
                val code = response?.code()
                if (response!!.isSuccessful()) {
                    val body = response.body() ?: return
                    callBack(body)
                } else {
                    Log.d("LoginService.getMessage", "Not 200 status code $code")
                    callBack(null)
                }
            }
        })
    }

    fun getTokens(response: AuthorizeResponse, callBack: (AuthorizeRequest?) -> Unit) {
        val Call = interfaceService.getTokens(response)

        Call.enqueue(object : Callback<AuthorizeRequest> {
            override fun onFailure(call: Call<AuthorizeRequest>?, t: Throwable?) {
                Log.d("LoginService.", t!!.message)
                callBack(null)
            }

            override fun onResponse(call: Call<AuthorizeRequest>?, response: Response<AuthorizeRequest>?) {
                if (response!!.isSuccessful()) {
                    val body = response.body() ?: return
                    callBack(body)
                } else {
                    Log.d("LoginMessage.getTokens", "Not 200 status code")
                    callBack(null)
                }
            }
        })
    }

    fun refreshTokens(response: RefreshResponce, callBack: (AuthorizeRequest?) -> Unit) {
        val Call = interfaceService.refreshTokens(response)

        Call.enqueue(object : Callback<AuthorizeRequest> {
            override fun onFailure(call: Call<AuthorizeRequest>?, t: Throwable?) {
                Log.d("LoginService.", t!!.message)
                callBack(null)
            }

            override fun onResponse(call: Call<AuthorizeRequest>?, response: Response<AuthorizeRequest>?) {
                if (response!!.isSuccessful()) {
                    val body = response.body() ?: return
                    callBack(body)
                } else {
                    Log.d("LoginMessage.refreshTok", "Not 200 status code")
                    callBack(null)
                }
            }
        })
    }

}
package beri.android.supportModules.service

import android.util.Log
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Model.User
import beri.android.supportModules.ServiceInterface.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService(private val interfaceService: InterfaceService) {

    fun getUser(callBack: (User?) -> Unit) {
        val Call = interfaceService.getUser()

        Call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.d("Subcategory.OnFailure", t!!.message)
                callBack(null)
            }
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                val code = response?.code()
                if (response!!.isSuccessful()) {
                    val body = response.body() ?: return
                    callBack(body)
                } else {
                    Log.d("Subcategory.getSubcat", "Not 200 status code $code")
                    callBack(null)
                }
            }
        })
    }

}
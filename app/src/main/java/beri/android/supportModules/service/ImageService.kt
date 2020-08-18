package beri.android.supportModules.service

import android.util.Log
import beri.android.supportModules.Model.Image
import beri.android.supportModules.ServiceInterface.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageService(private val interfaceService: InterfaceService) {

    fun getMessage(id: String, callBack: (Image?) -> Unit) {
        val Call = interfaceService.getImagesById(id)
        //println(id)

        Call.enqueue(object : Callback<Image> {
            override fun onFailure(call: Call<Image>?, t: Throwable?) {
                Log.d("LoginService.OnFailure", t!!.message)
                callBack(null)
            }

            override fun onResponse(call: Call<Image>?, response: Response<Image>?) {
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
}
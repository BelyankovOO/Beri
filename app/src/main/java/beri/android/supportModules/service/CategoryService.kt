package beri.android.supportModules.service

import android.util.Log
import beri.android.supportModules.DataService.CategoryRequest
import beri.android.supportModules.DataService.IdentifyRequest
import beri.android.supportModules.DataService.IdentifyResponse
import beri.android.supportModules.ServiceInterface.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService(private val interfaceService: InterfaceService){

    fun getCategory(callBack: (List<CategoryRequest?>) -> Unit) {
        val Call = interfaceService.getCategories()

        Call.enqueue(object : Callback<List<CategoryRequest>> {
            override fun onFailure(call: Call<List<CategoryRequest>>?, t: Throwable?) {
                Log.d("CategorySer.OnFailure", t!!.message)
                callBack(emptyList())
            }

            override fun onResponse(call: Call<List<CategoryRequest>>?, response: Response<List<CategoryRequest>>?) {
                val code = response?.code()
                if (response!!.isSuccessful()) {
                    val body = response.body() ?: return
                    //println(body)
                    callBack(body)
                } else {
                    Log.d("CategoryService.getCat", "Not 200 status code $code")
                    callBack(emptyList())
                }
            }
        })
    }

    

}
package beri.android.supportModules.service

import android.util.Log
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.ServiceInterface.InterfaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubcategoryService(private val interfaceService: InterfaceService) {

    fun getSubcategories(categoryId: String, callBack: (List<Subcategory>?) -> Unit) {
        val Call = interfaceService.getSubcategories(categoryId)

        Call.enqueue(object : Callback<List<Subcategory>> {
            override fun onFailure(call: Call<List<Subcategory>>?, t: Throwable?) {
                Log.d("Subcategory.OnFailure", t!!.message)
                callBack(null)
            }

            override fun onResponse(call: Call<List<Subcategory>>?, response: Response<List<Subcategory>>?) {
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
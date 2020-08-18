package beri.android.supportModules.Retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AuthorizationDelegate {

    companion object{
        val instance: AuthorizationDelegate by lazy{
            println("Authorize")
            AuthorizationDelegate()
        }
    }

    private var authorizationStatus: MutableLiveData<Boolean> = MutableLiveData()
    fun getAuthorizationStatus(): LiveData<Boolean>{
        return authorizationStatus
    }

    fun setData(status: Boolean){
        println("set value $status")
        authorizationStatus.postValue(status)
    }

}
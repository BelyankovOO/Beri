package beri.android.supportModules.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import beri.android.supportModules.Model.User
import beri.android.supportModules.Repository.UserRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    var userRepository: UserRepository? = null

    data class ProfileSummaryViewData(
        var id: String? = "",
        var phoneNumber: String? = "",
        var imageUrl: String? = ""
    )

    private fun UserToProfileSummaryViewData(user: User?): ProfileSummaryViewData{
        return ProfileSummaryViewData(
            user?.id,
            user?.phoneNumber,
            user?.image?.thumbnails?.get(0)?.url
        )
    }

    fun getUser(callback: (ProfileSummaryViewData?) -> Unit){
        val userRepository = userRepository ?: return
        userRepository.getUser {
            callback(UserToProfileSummaryViewData(it))
        }
    }

    fun removeTokens(){
        val userRepository = userRepository ?: return
        userRepository.removeTokens()
    }
}
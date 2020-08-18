package beri.android.modules.ProfileModule

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import beri.android.modules.CategoryModule.CategoryActivity
import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.Help.ModeOfProfileView
import beri.android.supportModules.Repository.UserRepository
import beri.android.supportModules.Retrofit.AuthorizationDelegate
import beri.android.supportModules.Retrofit.RetrofitInstance
import beri.android.supportModules.ViewModels.ProfileViewModel
import beri.android.supportModules.service.UserService

class ProfileInteractor(private var activity: ProfileActivity): ProfileContract.Interactor{
    private lateinit var authorizationDelegate: AuthorizationDelegate
    private lateinit var userService: UserService
    private lateinit var tokenDao: TokenDao
    private lateinit var profileViewModel: ProfileViewModel

    override fun setup() {
        authorizationDelegate = AuthorizationDelegate.instance
        userService = UserService(RetrofitInstance.instance(activity))
        tokenDao = BeriDatabase.getInstance(activity).TokenDao()
        profileViewModel = ViewModelProvider(activity).get(ProfileViewModel::class.java)
        profileViewModel.userRepository = UserRepository(userService, tokenDao)
    }

    override fun getModeOfScreen(callback: (ModeOfProfileView) -> Unit) {
        authorizationDelegate.getAuthorizationStatus().observe(activity, Observer{
            if(it == true) {
                callback(ModeOfProfileView.SIGNOUT)
            } else {
                callback(ModeOfProfileView.SIGNIN)
            }
        })
    }

    override fun getUserParameter(callback: (ProfileViewModel.ProfileSummaryViewData?) -> Unit) {
        profileViewModel.getUser {
            println(it)
            callback(it)
        }
    }

    override fun removeTokens() {
        profileViewModel.removeTokens()
    }
}
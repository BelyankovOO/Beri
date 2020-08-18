package beri.android.modules.LoginModule

import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.DataService.AuthorizeRequest
import beri.android.supportModules.DataService.IdentifyRequest
import beri.android.supportModules.Repository.LoginRepository
import beri.android.supportModules.Retrofit.AuthorizationDelegate
import beri.android.supportModules.Retrofit.RetrofitInstance
import beri.android.supportModules.service.*

class LoginInteractor(private var activity: LoginActivity): LoginContract.Interactor{

    private lateinit var loginService: LoginService
    private lateinit var tokenDao: TokenDao
    private lateinit var loginRepository: LoginRepository
    private lateinit var authorizeDelegate: AuthorizationDelegate

    override fun setup() {
        authorizeDelegate = AuthorizationDelegate.instance
        loginService = LoginService(RetrofitInstance.instance(activity))
        tokenDao = BeriDatabase.getInstance(activity).TokenDao()
        loginRepository = LoginRepository(tokenDao, loginService)
    }

    override fun identify(phoneNumber: String, callback: (IdentifyRequest?) -> Unit) {
        loginRepository.getMessage(phoneNumber){
            callback(it)
        }
    }

    override fun authorize(messageCode: String, identifyRequest: IdentifyRequest?, callback: (AuthorizeRequest?) -> Unit) {
        identifyRequest?.let {
            loginRepository.getTokens(messageCode, identifyRequest){
                authorizeDelegate.setData(true)
                callback(it)
            }
        }
    }
}
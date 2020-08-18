package beri.android.supportModules.Repository

import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.DataService.AuthorizeRequest
import beri.android.supportModules.DataService.AuthorizeResponse
import beri.android.supportModules.DataService.IdentifyRequest
import beri.android.supportModules.DataService.IdentifyResponse
import beri.android.supportModules.Model.Token
import beri.android.supportModules.service.LoginService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginRepository(private val tokenDao: TokenDao, private val loginService: LoginService){

    fun getMessage(phoneNumber: String, callback: (IdentifyRequest?) -> Unit){
        val identifyResponse = IdentifyResponse(phoneNumber)
        loginService.getMessage(identifyResponse){
            callback(it)
        }
    }

    fun getTokens(messageCode: String, identifyRequest: IdentifyRequest, callback: (AuthorizeRequest?) -> Unit){
        val authorizeResponse = AuthorizeResponse(messageCode, identifyRequest.messageId, identifyRequest.userId)
        loginService.getTokens(authorizeResponse){
            saveTokensInDatabase(it?.authorization, it?.refresh)
            callback(it)
        }
    }

    private fun saveTokensInDatabase(accessToken: Token?, resreshToken: Token?){
        accessToken ?: return
        resreshToken ?: return
        GlobalScope.launch {
            tokenDao.insertTokens(accessToken, resreshToken)
            println("saved ${tokenDao.getAllTokens()}")
        }
    }
}


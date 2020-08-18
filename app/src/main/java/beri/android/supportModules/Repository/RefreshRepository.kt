package beri.android.supportModules.Repository

import android.app.Instrumentation
import androidx.lifecycle.LiveData
import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.DataService.AuthorizeRequest
import beri.android.supportModules.Model.Token
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RefreshRepository(private val tokenDao: TokenDao) {

    enum class TokenType(val type: String) {
        AUTHORIZATION("AUTHORIZATION"),
        REFRESH("REFRESH");
    }

    fun getAccessToken(): String? {
        val token = tokenDao.getToken("AUTHORIZATION")
        //println(token)
        return token?.token
    }

    fun getRefreshToken(): String? {
        val token = tokenDao.getToken("REFRESH")
        //println(token)
        return token?.token
    }

    fun updateTokens(authorizeRequest: AuthorizeRequest?){
        val accessToken = authorizeRequest?.authorization ?: return
        val refreshToken = authorizeRequest.refresh

        GlobalScope.launch {
            tokenDao.updateTokens(accessToken, refreshToken)
        }
    }

}
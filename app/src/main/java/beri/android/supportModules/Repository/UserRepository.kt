package beri.android.supportModules.Repository

import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.Model.User
import beri.android.supportModules.service.UserService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository(private val userService: UserService, private val tokenDao: TokenDao) {

    fun removeTokens(){
        GlobalScope.launch {
            tokenDao.removeToken("AUTHORIZATION")
            tokenDao.removeToken("REFRESH")
        }
    }

    fun getUser(callback: (User?) -> Unit){
        userService.getUser {
            callback(it)
        }
    }

}
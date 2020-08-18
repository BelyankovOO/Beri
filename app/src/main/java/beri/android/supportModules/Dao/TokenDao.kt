package beri.android.supportModules.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import beri.android.supportModules.Model.Token

@Dao
interface TokenDao {

    @Query("SELECT * FROM Token WHERE tokenType = :tokenType")
    fun getToken(tokenType: String): Token?

    @Query("SELECT * FROM Token ORDER BY tokenType ")
    fun getAllTokens(): List<Token>?

    @Query("DELETE FROM Token WHERE tokenType = :tokenType")
    fun removeToken(tokenType: String)

    @Insert(onConflict = REPLACE)
    fun insertToken(token: Token)

    @Insert(onConflict = REPLACE)
    fun insertTokens(accessToken: Token, resfreshToken: Token)

    @Update
    fun updateTokens(accessToken: Token, refreshToken: Token)
}
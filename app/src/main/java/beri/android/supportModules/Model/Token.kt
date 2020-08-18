package beri.android.supportModules.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    var createdAt: String = "",
    var expireDate: String = "",
    var id: String = "",
    var token: String = "",
    @PrimaryKey var tokenType: String = ""
)

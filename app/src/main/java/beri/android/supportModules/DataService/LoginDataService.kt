package beri.android.supportModules.DataService

import beri.android.supportModules.Model.Token
import beri.android.supportModules.Model.User

//IdentifyData

data class IdentifyRequest(
    val messageCodeType: String,
    val messageId: String,
    val phoneNumber: String,
    val userId: String
)

data class IdentifyResponse(
    val phoneNumber: String
)

//AuthorizeData

data class AuthorizeRequest(
    val authorization: Token,
    val refresh: Token,
    val user: User
)

data class AuthorizeResponse(
    val messageCode: String,
    val messageId: String,
    val userId: String
)


//RefreshData

data class RefreshResponce(
    val token: String?
)

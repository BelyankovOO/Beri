package beri.android.supportModules.DataService

enum class ProfileRowType{
    IMAGE,
    ITEM,
    BUTTON
}

data class ProfileRow(
    var type: ProfileRowType,
    var image: String?,
    var item: ProfileItem?,
    var button: ProfileButton?
)

data class ProfileItem(
    var phoneNumber: String?,
    var phoneDescription: String?
)

data class ProfileButton(
    var buttonText: String
)
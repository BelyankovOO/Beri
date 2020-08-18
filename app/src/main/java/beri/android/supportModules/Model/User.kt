package beri.android.supportModules.Model

data class User(
    var authority: String,
    var createdAt: String,
    var enabled: Boolean,
    var id: String,
    var image: Image,
    var imageId: String,
    var lastModifiedAt: String,
    var phoneNumber: String,
    var verified: Boolean
)
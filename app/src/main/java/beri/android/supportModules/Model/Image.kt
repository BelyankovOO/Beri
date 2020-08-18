package beri.android.supportModules.Model


data class Image(
    //var createdAt: String,
    //var createdBy: String,
    var id: String = "",
    //var originalFilename: String,
    //var originalWeight: Int,
    //var purpose: String,
    var thumbnails: List<Thumbnail> = listOf()
    //var type: String
)
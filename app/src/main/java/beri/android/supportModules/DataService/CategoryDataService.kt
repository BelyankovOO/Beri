package beri.android.supportModules.DataService

import beri.android.supportModules.Help.I18N

import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Model.Thumbnail

//Get Category

data class CategoryRequest(
    var createdAt: String = "",
    var createdBy: String = "",
    var i18n: MutableList<I18NRequest>? = null,
    var id: String = "",
    var image: ImageRequest? = null,
    var imageId: String = "",
    var lastModifiedAt: String = "",
    var lastModifiedBy: String = "",
    var name: String = "",
    var subcategories: MutableList<SubcategoryRequest>? = null)
{
    data class SubcategoryRequest(
        var categoryId: String? = null,
        var createdAt: String? = null,
        var createdBy: String? = null,
        var i18n: MutableList<I18NRequest>? = null,
        var id: String? = null,
        var lastModifiedAt: String? = null,
        var lastModifiedBy: String? = null,
        var name: String? = null
    )

    data class ImageRequest(
        var createdAt: String? = null,
        var createdBy: String? = null,
        var id: String? = null,
        var originalFilename: String? = null,
        var originalWeight: Int? = null,
        var purpose: String? = null,
        var thumbnails: MutableList<ThumbnailRequest>? = null,
        var type: String? = null
    )
    {
        data class ThumbnailRequest(
            var createdAt: String? = null,
            var createdBy: String? = null,
            var filename: String? = null,
            var height: Int? = null,
            var id: String? = null,
            var url: String? = null,
            var weight: Int? = null,
            var width: Int? = null
        )
    }

    data class I18NRequest(
        var languageCode: String? = null,
        var value: String? = null
    )
}
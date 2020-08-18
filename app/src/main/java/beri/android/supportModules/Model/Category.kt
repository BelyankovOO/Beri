package beri.android.supportModules.Model

import beri.android.supportModules.Help.I18N

data class Category(
    //var createdAt: String,
    //var createdBy: String,
    //var i18n: List<I18N>? = listOf(),
    var id: String = "",
    var image: Image? = null,
    //var imageId: String,
    //var lastModifiedAt: String,
    //var lastModifiedBy: String,
    var name: String = "",
    var subcategories: List<Subcategory> = listOf()
)
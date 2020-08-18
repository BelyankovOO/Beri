package beri.android.supportModules.DataService

import beri.android.supportModules.ViewModels.SubcategoryViewModel

enum class RowType{
    HEADER,
    SUBCATEGORY
}

data class SubcategoryRow(
    var type: RowType,
    var subcategory: SubcategoryViewModel.SubcategorySummaryViewData?,
    var header: String?
)

data class SubcategoryItem(
    var name: String,
    var imageType: String
)
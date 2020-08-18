package beri.android.modules.CategoryModule

import beri.android.modules.LoginModule.LoginActivity
import beri.android.modules.SubcategoryModule.SubcategoryActivity
import beri.android.supportModules.ViewModels.CategoryViewModel

class CategoryRouter(private var activity: CategoryActivity): CategoryContract.Router {
    override fun navigateToSubcategory(categorySummaryViewData: CategoryViewModel.CategorySummaryViewData) {
        //val extra = categorySummaryViewData ?: return
        SubcategoryActivity.launch(activity, categorySummaryViewData)
    }
}
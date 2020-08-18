package beri.android.modules.CategoryModule

import beri.android.modules.LoginModule.LoginActivity
import beri.android.supportModules.ViewModels.CategoryViewModel

interface CategoryContract {
    interface View{
        fun setDataToAdapter(categorySummaryViewList: List<CategoryViewModel.CategorySummaryViewData>)
        fun showProgressBar()
        fun hideProgressBar()
        fun showMessage(message: String)
    }

    interface Presenter{
        fun categoryClicked(categorySummaryViewData: CategoryViewModel.CategorySummaryViewData)
        fun viewDidLoad()
    }

    interface Router{
        fun navigateToSubcategory(categorySummaryViewData: CategoryViewModel.CategorySummaryViewData)
    }

    interface Configurator{
        fun configurate(view: CategoryActivity)
    }

    interface Interactor{
        fun setup()
        fun loadCategories(callback: (List<CategoryViewModel.CategorySummaryViewData>?) -> Unit)
    }

    interface InteractorOutput{
        fun onRequestSuccess(categorySummaryViewList: List<CategoryViewModel.CategorySummaryViewData>)
        fun onRequestError()
    }
}
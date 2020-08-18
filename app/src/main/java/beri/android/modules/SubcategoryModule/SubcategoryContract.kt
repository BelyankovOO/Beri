package beri.android.modules.SubcategoryModule

import beri.android.supportModules.ViewModels.SubcategoryViewModel

interface SubcategoryContract {
    interface View{
        fun showProgressBar()
        fun hideProgressBar()
        fun onShowMessage(message: String)
        fun setDataInAdapter(subcategoryList: List<SubcategoryViewModel.SubcategorySummaryViewData>)
        fun getDataFromAdapter(): List<SubcategoryViewModel.SubcategorySummaryViewData>
    }

    interface Presenter{
        fun viewDidLoad(categoryId: String)
        fun onDestroy()
    }

    interface Router{

    }

    interface Configurator{
        fun configurate(view: SubcategoryActivity)
    }

    interface Interactor{
        fun setup()
        fun loadSubcategories(categoryId: String, callback: (List<SubcategoryViewModel.SubcategorySummaryViewData>?) -> Unit)
        fun updateDataInDatabaseCheck(subcategoryList: List<SubcategoryViewModel.SubcategorySummaryViewData>)
    }

    interface InteractorOutput{
        fun onRequestDatabaseSuccess(subcategoryList: List<SubcategoryViewModel.SubcategorySummaryViewData>)
        fun onRequestDatabaseError(message: String)
    }

}
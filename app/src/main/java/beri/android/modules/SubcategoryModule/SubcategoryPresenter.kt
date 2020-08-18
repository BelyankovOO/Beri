package beri.android.modules.SubcategoryModule

import beri.android.supportModules.ViewModels.SubcategoryViewModel

class SubcategoryPresenter(private var view: SubcategoryContract.View): SubcategoryContract.Presenter, SubcategoryContract.InteractorOutput {
    var router: SubcategoryContract.Router? = null
    var interactor: SubcategoryContract.Interactor? = null

    override fun viewDidLoad(categoryId: String) {
        interactor?.setup()
        interactor?.loadSubcategories(categoryId){result ->
            if(result != null){
                onRequestDatabaseSuccess(result)
            } else {
                onRequestDatabaseError("No subcategories in database")
            }
        }
    }

    override fun onDestroy() {
        val subcategoryList = view.getDataFromAdapter()
        interactor?.updateDataInDatabaseCheck(subcategoryList)
        router = null
        interactor = null
    }

    override fun onRequestDatabaseSuccess(subcategoryList: List<SubcategoryViewModel.SubcategorySummaryViewData>) {
        view.setDataInAdapter(subcategoryList)
    }

    override fun onRequestDatabaseError(message: String) {
        view.onShowMessage(message)
    }
}
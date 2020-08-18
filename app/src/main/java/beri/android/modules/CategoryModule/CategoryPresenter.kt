package beri.android.modules.CategoryModule

import beri.android.modules.LoginModule.LoginContract
import beri.android.supportModules.ViewModels.CategoryViewModel

class CategoryPresenter(private var view: CategoryContract.View): CategoryContract.Presenter, CategoryContract.InteractorOutput{
    var router: CategoryContract.Router? = null
    var interactor: CategoryContract.Interactor? = null

    override fun categoryClicked(categorySummaryViewData: CategoryViewModel.CategorySummaryViewData) {
        router?.navigateToSubcategory(categorySummaryViewData)
    }

    override fun viewDidLoad() {
        view.showProgressBar()
        interactor?.setup()
        interactor?.loadCategories(){ result ->
            if(result != null){
                onRequestSuccess(result)
            } else {
                onRequestError()
            }
        }
    }

    override fun onRequestSuccess(categorySummaryViewList: List<CategoryViewModel.CategorySummaryViewData>) {
        view.hideProgressBar()
        view.setDataToAdapter(categorySummaryViewList)
    }

    override fun onRequestError() {
        view.hideProgressBar()
        view.showMessage("Error server request")
    }

}
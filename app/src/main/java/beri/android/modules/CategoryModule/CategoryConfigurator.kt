package beri.android.modules.CategoryModule

import beri.android.modules.LoginModule.*

class CategoryConfigurator: CategoryContract.Configurator{

    override fun configurate(view: CategoryActivity) {
        val presenter = CategoryPresenter(view)
        val router = CategoryRouter(view)
        val interactor = CategoryInteractor(view)

        presenter.router = router
        view.presenter = presenter
        presenter.interactor = interactor
    }

}
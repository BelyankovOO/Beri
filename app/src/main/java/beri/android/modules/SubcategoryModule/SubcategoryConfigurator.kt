package beri.android.modules.SubcategoryModule

class SubcategoryConfigurator: SubcategoryContract.Configurator {

    override fun configurate(view: SubcategoryActivity) {
        val presenter = SubcategoryPresenter(view)
        val router = SubcategoryRouter(view)
        val interactor = SubcategoryInteractor(view)

        presenter.router = router
        view.presenter = presenter
        presenter.interactor = interactor
    }

}
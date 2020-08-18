package beri.android.modules.MainModule


class MainConfigurator() : MainContract.Configurator {

    override fun configurate(view: MainActivity) {
        val presenter = MainPresenter(view)
        val router = MainRouter(view)
        val interactor = MainInteractor(view)

        presenter.router = router
        presenter.interactor = interactor
        view.presenter = presenter
    }

}
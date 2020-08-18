package beri.android.modules.ProfileModule


class ProfileConfigurator: ProfileContract.Configurator {

    override fun configurate(view: ProfileActivity) {
        val presenter = ProfilePresenter(view)
        val router = ProfileRouter(view)
        val interactor = ProfileInteractor(view)

        presenter.router = router
        view.presenter = presenter
        presenter.interactor = interactor
    }
}
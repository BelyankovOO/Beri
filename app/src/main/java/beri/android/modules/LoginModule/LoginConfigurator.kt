package beri.android.modules.LoginModule

class LoginConfigurator: LoginContract.Configurator{

    override fun configurate(view: LoginActivity) {
        val presenter = LoginPresenter(view)
        val router = LoginRouter(view)
        val interactor = LoginInteractor(view)

        presenter.router = router
        view.presenter = presenter
        presenter.interactor = interactor
    }

}
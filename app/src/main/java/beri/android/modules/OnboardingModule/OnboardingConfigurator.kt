package beri.android.modules.OnboardingModule

class OnboardingConfigurator: OnboardingContract.Configurator {

    override fun configurate(view: OnboardingActivity) {
        val presenter = OnboardingPresenter(view)
        val router = OnboardingRouter(view)

        presenter.router = router
        view.presenter = presenter
    }
}
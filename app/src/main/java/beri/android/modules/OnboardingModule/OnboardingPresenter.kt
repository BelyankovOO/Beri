package beri.android.modules.OnboardingModule

class OnboardingPresenter(private var view: OnboardingContract.View?): OnboardingContract.Presenter {

    var router: OnboardingContract.Router? = null

    override fun onViewCreated() {
    }

    override fun OnDestroy() {
        view = null
    }

    override fun skipButtonClicked() {
        router?.navigateToMap()
    }

    override fun enterButtonClicked() {
        router?.navigateToLogin()
    }
}
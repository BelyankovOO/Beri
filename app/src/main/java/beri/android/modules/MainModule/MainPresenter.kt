package beri.android.modules.MainModule

import android.os.Handler
import android.util.Log

class MainPresenter(private var view: MainContract.View?): MainContract.Presenter, MainContract.InteractorOutput{

    var router: MainContract.Router? = null
    var interactor: MainContract.Interactor? = null

    override fun onViewCreated() {
        interactor?.checkUserAuthority {
            println("user $it")
        }
        interactor?.checkOnboardingStatus {result ->
            if(result == null) {
                onOnboardingStatusFalse()
            } else {
                onOnboardingStatusTrue()
            }
        }
    }

    override fun onDestroy() {
        router = null
        view = null
    }

    override fun viewDidLoad() {
        interactor?.setup()
    }

    override fun onOnboardingStatusTrue() {
        Handler().postDelayed({
            router?.navigateToMap()
            view?.finishView()}, 1500)
    }

    override fun onOnboardingStatusFalse() {
        interactor?.setOnboardingStatus(true)
        Handler().postDelayed({
            router?.navigateToOnboarding()
            view?.finishView()}, 1500)
    }
}
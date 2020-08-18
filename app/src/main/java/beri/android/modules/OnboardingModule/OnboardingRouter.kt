package beri.android.modules.OnboardingModule

import beri.android.modules.LoginModule.LoginActivity
import beri.android.modules.MapModule.MapActivity

class OnboardingRouter(private val activity: OnboardingActivity): OnboardingContract.Router {

    override fun navigateToLogin() {
        LoginActivity.launch(activity)
    }

    override fun navigateToMap() {
        MapActivity.launch(activity)
    }
}
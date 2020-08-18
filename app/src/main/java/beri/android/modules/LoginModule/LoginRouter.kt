package beri.android.modules.LoginModule

import beri.android.modules.MapModule.MapActivity

class LoginRouter(private var activity: LoginActivity): LoginContract.Router {
    override fun navigateToMap() {
        MapActivity.launch(activity)
    }

    override fun navigateBack() {
        activity.finish()
    }
}
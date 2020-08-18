package beri.android.modules.ProfileModule

import beri.android.modules.LoginModule.LoginActivity

class ProfileRouter(private var activity: ProfileActivity): ProfileContract.Router{

    override fun navigateToLogin() {
        LoginActivity.launch(activity)
    }

}
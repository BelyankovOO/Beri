package beri.android.modules.MainModule

import android.util.Log
import beri.android.modules.LoginModule.LoginActivity
import beri.android.modules.MapModule.MapActivity
import beri.android.modules.OnboardingModule.OnboardingActivity


class MainRouter(private val activity: MainActivity): MainContract.Router {

    override fun navigateToOnboarding(){
        OnboardingActivity.launch(activity)
    }

    override fun navigateToMap(){
        MapActivity.launch(activity)
    }

    override fun navigateToLogin(){
        LoginActivity.launch(activity)
    }

}
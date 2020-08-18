package beri.android.modules.MainModule

import beri.android.modules.MainModule.MainActivity
import beri.android.supportModules.Model.User

interface MainContract{
    interface View{
        fun finishView()
    }

    interface Presenter{
        fun onViewCreated()
        fun onDestroy()
        fun viewDidLoad()
    }

    interface Router{
        fun navigateToOnboarding()
        fun navigateToLogin()
        fun navigateToMap()
    }

    interface Configurator{
        fun configurate(view: MainActivity)
    }

    interface Interactor{
        fun setup()
        fun checkUserAuthority(callback: (User?) -> Unit)
        fun checkOnboardingStatus(callback: (Boolean?) -> Unit)
        fun setOnboardingStatus(status: Boolean)
    }

    interface InteractorOutput{
        fun onOnboardingStatusTrue()
        fun onOnboardingStatusFalse()
    }


}
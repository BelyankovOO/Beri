package beri.android.modules.OnboardingModule

import beri.android.modules.OnboardingModule.OnboardingActivity

interface OnboardingContract {
    interface View{
        fun finishView()
    }

    interface Presenter{
        fun onViewCreated()
        fun OnDestroy()
        fun skipButtonClicked()
        fun enterButtonClicked()
    }

    interface Router{
        fun navigateToLogin()
        fun navigateToMap()
    }

    interface Configurator{
        fun configurate(view: OnboardingActivity)
    }
}
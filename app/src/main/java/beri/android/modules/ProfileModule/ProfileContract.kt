package beri.android.modules.ProfileModule

import beri.android.supportModules.Help.ModeOfProfileView
import beri.android.supportModules.ViewModels.ProfileViewModel

interface ProfileContract {
    interface View{
        fun setupView(mode: ModeOfProfileView?)
        fun setUserData(userSummaryViewData: ProfileViewModel.ProfileSummaryViewData)
        fun showMessage(message: String)
    }

    interface Presenter{
        fun viewDidLoad()
        fun getViewMode()
        fun setupUserParameter()
        fun signOutButtonClicked()
        fun changeModeOfScreen()
        fun signInButtonClicked()
    }

    interface Configurator{
        fun configurate(view: ProfileActivity)
    }

    interface Interactor{
        fun setup()
        fun getModeOfScreen(callback: (ModeOfProfileView) -> Unit)
        fun getUserParameter(callback: (ProfileViewModel.ProfileSummaryViewData?) -> Unit)
        fun removeTokens()
    }

    interface Router{
        fun navigateToLogin()
    }

    interface InteractorOutput{
        fun onModeRequestSuccess(mode: ModeOfProfileView)
        fun onModeRequestError()
        fun onUserRequestSuccess(userSummaryViewData: ProfileViewModel.ProfileSummaryViewData)
        fun onUserRequestError()
    }
}
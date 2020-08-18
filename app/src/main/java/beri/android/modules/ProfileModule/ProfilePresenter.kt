package beri.android.modules.ProfileModule

import beri.android.supportModules.Help.ModeOfProfileView
import beri.android.supportModules.ViewModels.ProfileViewModel


class ProfilePresenter(private var view: ProfileContract.View): ProfileContract.Presenter, ProfileContract.InteractorOutput {
    var router: ProfileContract.Router? = null
    var interactor: ProfileContract.Interactor? = null

    private var modeOfScreen: ModeOfProfileView? = null

    override fun viewDidLoad() {

    }

    override fun getViewMode() {
        interactor?.setup()
        interactor?.getModeOfScreen {
            onModeRequestSuccess(it)
        }
    }

    override fun setupUserParameter() {
        println("worked3 $modeOfScreen")
        when(modeOfScreen) {
            ModeOfProfileView.SIGNOUT -> {
                println("worked")
                interactor?.getUserParameter { result ->
                    if (result != null) {
                        onUserRequestSuccess(result)
                    } else {
                        onUserRequestError()
                    }
                }
            }
            ModeOfProfileView.SIGNIN -> {
                return
            }
        }
    }

    override fun signOutButtonClicked() {
        interactor?.removeTokens()
        changeModeOfScreen()
    }

    override fun signInButtonClicked() {
        router?.navigateToLogin()
    }

    override fun changeModeOfScreen() {
        when(modeOfScreen){
            ModeOfProfileView.SIGNOUT -> modeOfScreen = ModeOfProfileView.SIGNIN
            ModeOfProfileView.SIGNIN -> modeOfScreen = ModeOfProfileView.SIGNOUT
        }
        println(modeOfScreen)
        view.setupView(modeOfScreen)
    }

    override fun onModeRequestSuccess(mode: ModeOfProfileView) {
        modeOfScreen = mode
        println(modeOfScreen)
        view.setupView(modeOfScreen)
    }

    override fun onModeRequestError() {
        view.showMessage("SetupError")
    }

    override fun onUserRequestSuccess(userSummaryViewData: ProfileViewModel.ProfileSummaryViewData) {
        view.setUserData(userSummaryViewData)
    }

    override fun onUserRequestError() {
        view.showMessage("GetUserDataError")
    }

}
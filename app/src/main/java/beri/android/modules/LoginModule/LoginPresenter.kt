package beri.android.modules.LoginModule

import beri.android.supportModules.DataService.AuthorizeRequest
import beri.android.supportModules.DataService.IdentifyRequest
import beri.android.supportModules.Help.ModeOfLoginView

class LoginPresenter(private var view: LoginContract.View): LoginContract.Presenter, LoginContract.InteractorOutput {

    var router: LoginContract.Router? = null
    var interactor: LoginContract.Interactor? = null

    private var modeOfScreen: ModeOfLoginView = ModeOfLoginView.IDENTIFICATION
    private var identifyRequest: IdentifyRequest? = null

    override fun onViewCreated() {
        interactor?.setup()
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun backButtonCliked() {
        router?.navigateBack()
    }

    override fun enterButtonClicked() {
        when(modeOfScreen){
            ModeOfLoginView.IDENTIFICATION -> {
                val phoneNumber = view.editTextContent() ?: return
                interactor?.identify(phoneNumber){ result ->
                    if(result != null){
                        onIdentifyRequestSuccess(result)
                    } else {
                        onIdentifyRequestError()
                    }
                }
            }
            ModeOfLoginView.AUTHORIZE -> {
                val code = view.editTextContent() ?: return
                interactor?.authorize(code, identifyRequest){ result ->
                    if(result != null){
                        onAuthorizeRequestSuccess(result)
                    } else {
                        println("Error1122")
                        onAuthorizeRequestError()
                    }
                }
            }
        }
    }

    override fun cancelButtonClicked() {
        changeMod(ModeOfLoginView.IDENTIFICATION)
    }

    override fun changeMod(modeOfScreen: ModeOfLoginView) {
        this.modeOfScreen = modeOfScreen
        view.changeMode(modeOfScreen)
    }

    override fun onIdentifyRequestSuccess(identifyRequest: IdentifyRequest) {
        this.identifyRequest = identifyRequest
        changeMod(ModeOfLoginView.AUTHORIZE)
    }

    override fun onAuthorizeRequestSuccess(authorizeRequest: AuthorizeRequest) {
        router?.navigateToMap()
    }

    override fun onIdentifyRequestError() {
        view.onShowMessage("Error identify request")
    }

    override fun onAuthorizeRequestError() {
        view.onShowMessage("Error authorize request")
    }
}
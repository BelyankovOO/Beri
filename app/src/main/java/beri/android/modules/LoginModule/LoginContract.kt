package beri.android.modules.LoginModule

import beri.android.supportModules.DataService.AuthorizeRequest
import beri.android.supportModules.DataService.IdentifyRequest
import beri.android.supportModules.Help.ModeOfLoginView

interface LoginContract {
    interface View{
        fun finishView()
        fun configurateView(modeOfScreen: ModeOfLoginView)
        fun changeMode(modeOfScreen: ModeOfLoginView)
        fun editTextContent(): String?
        fun onShowMessage(message: String)
    }

    interface Presenter{
        fun onViewCreated()
        fun onDestroy()
        fun backButtonCliked()
        fun enterButtonClicked()
        fun cancelButtonClicked()
        fun changeMod(modeOfScreen: ModeOfLoginView)
    }

    interface Router{
        fun navigateToMap()
        fun navigateBack()
    }

    interface Configurator{
        fun configurate(view: LoginActivity)
    }

    interface Interactor{
        fun identify(phoneNumber: String, callback: (IdentifyRequest?) -> Unit)
        fun authorize(messageCode: String, identifyRequest: IdentifyRequest?, callback: (AuthorizeRequest?) -> Unit)
        fun setup()
    }

    interface InteractorOutput{
        fun onIdentifyRequestSuccess(identifyRequest: IdentifyRequest)
        fun onAuthorizeRequestSuccess(authorizeRequest: AuthorizeRequest)
        fun onIdentifyRequestError()
        fun onAuthorizeRequestError()
    }
}
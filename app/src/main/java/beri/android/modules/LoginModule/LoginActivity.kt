package beri.android.modules.LoginModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import beri.android.R
import beri.android.supportModules.Help.ModeOfLoginView

class LoginActivity: AppCompatActivity(), LoginContract.View {
    companion object{
        fun launch(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var modeOfScreen: ModeOfLoginView = ModeOfLoginView.IDENTIFICATION

    var configurator: LoginContract.Configurator? = null
    var presenter: LoginContract.Presenter? = null
    private lateinit var clueText: TextView
    private lateinit var enterText: EditText
    private lateinit var enterButton: Button
    private lateinit var cancelButton: Button
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        clueText = findViewById(R.id.clue_text)
        enterText = findViewById(R.id.enter_text)
        enterButton = findViewById(R.id.enter_button)
        cancelButton = findViewById(R.id.cancel_button)
        backButton = findViewById(R.id.back_button)

        if(configurator == null) configurator = LoginConfigurator()
        configurator?.configurate(this)

        configurateView(modeOfScreen)

        presenter?.onViewCreated()

        enterButton.setOnClickListener{presenter?.enterButtonClicked()}
        cancelButton.setOnClickListener{presenter?.cancelButtonClicked()}
        backButton.setOnClickListener{presenter?.backButtonCliked()}
    }

    override fun finishView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configurateView(modeOfScreen: ModeOfLoginView) {
        when(modeOfScreen){
            ModeOfLoginView.IDENTIFICATION -> {
                clueText.text = getString(R.string.clue_for_phone)
                enterText.hint = getString(R.string.phone_example)
                enterButton.text = getString(R.string.get_code)
                cancelButton.visibility = View.GONE
            }
            ModeOfLoginView.AUTHORIZE -> {
                clueText.text = getString(R.string.clue_for_code)
                enterText.hint = getString(R.string.code_example)
                enterButton.text = getString(R.string.enter_code)
                cancelButton.visibility = View.VISIBLE
            }
        }
    }

    override fun changeMode(modeOfScreen: ModeOfLoginView) {
        this.modeOfScreen = modeOfScreen
        enterText.getText().clear()
        configurateView(modeOfScreen)
    }

    override fun editTextContent(): String? {
        val text = enterText.getText().toString()
        if(TextUtils.isEmpty(text)){
            return null
        } else {
            return text
        }
    }

    override fun onShowMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
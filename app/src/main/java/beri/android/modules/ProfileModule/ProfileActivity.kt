package beri.android.modules.ProfileModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import beri.android.R
import beri.android.supportModules.Adapter.ProfileAdapter
import beri.android.supportModules.Help.ModeOfProfileView
import beri.android.supportModules.Retrofit.AuthorizationDelegate
import beri.android.supportModules.ViewModels.ProfileViewModel


class ProfileActivity: ProfileContract.View, ProfileAdapter.ProfileAdapterListener, AppCompatActivity() {

    companion object{
        fun launch(context: Context){
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
    }

    var configurator: ProfileContract.Configurator? = null
    var presenter: ProfileContract.Presenter? = null
    private lateinit var profileRecyclerView: RecyclerView
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var profileSignInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(configurator == null) configurator = ProfileConfigurator()
        configurator?.configurate(this)

        presenter?.getViewMode()
        //presenter?.setupUserParameter()
    }

    override fun onStart() {
        super.onStart()
        //presenter?.setupUserParameter()
    }

    override fun onResume() {
        super.onResume()
        presenter?.setupUserParameter()
    }

    override fun setupView(mode: ModeOfProfileView?) {
        if(mode == ModeOfProfileView.SIGNOUT){
            println("signout")
            setContentView(R.layout.activity_progile_sign_out)
            profileRecyclerView = findViewById(R.id.sign_out_recycler_view)
            profileRecyclerView.layoutManager = LinearLayoutManager(this)
            profileAdapter = ProfileAdapter(this, this)
            profileRecyclerView.adapter = profileAdapter
        } else {
            println("signin")
            setContentView(R.layout.activity_profile_sign_in)
            profileSignInButton = findViewById(R.id.sign_in_button)
            profileSignInButton.setOnClickListener {
                presenter?.signInButtonClicked()
            }
        }
    }

    override fun setUserData(userSummaryViewData: ProfileViewModel.ProfileSummaryViewData) {
        profileAdapter.setupStyleList(userSummaryViewData)
    }

    override fun onSignOut() {
        presenter?.signOutButtonClicked()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
package beri.android.modules.OnboardingModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import beri.android.R
import beri.android.supportModules.Adapter.OnboardingAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardingActivity: AppCompatActivity(), OnboardingContract.View {

    companion object{
        fun launch(context: Context){
            val intent = Intent(context, OnboardingActivity::class.java)
            context.startActivity(intent)
        }
    }

    var configurator: OnboardingContract.Configurator? = null
    var presenter: OnboardingContract.Presenter? = null
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: OnboardingAdapter
    private lateinit var dotsIndicator: WormDotsIndicator
    private lateinit var loginButton: Button
    private lateinit var skipButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        loginButton = findViewById(R.id.loginButton)
        skipButton = findViewById(R.id.skipButton)
        viewPager = findViewById(R.id.onboarding_pager)
        dotsIndicator = findViewById(R.id.dots_indicator)

        if(configurator == null) configurator = OnboardingConfigurator()

        configurator?.configurate(this)

        pagerAdapter = OnboardingAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        dotsIndicator.setViewPager(viewPager)

        loginButton.setOnClickListener {presenter?.enterButtonClicked()}
        skipButton.setOnClickListener {presenter?.skipButtonClicked()}
    }

    override fun onPause(){
        super.onPause()
    }

    override fun onResume(){
        super.onResume()
    }

    override fun onDestroy(){
        presenter = null
        super.onDestroy()
    }

    override fun finishView(){
        finish()
    }
}
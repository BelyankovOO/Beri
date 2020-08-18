package beri.android.modules.MainModule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import beri.android.R



class MainActivity: AppCompatActivity(), MainContract.View {

    var presenter: MainContract.Presenter? = null
    var configurator: MainContract.Configurator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(configurator == null) configurator = MainConfigurator()
        configurator?.configurate(this)

        presenter?.viewDidLoad()

    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun finishView() {
        finish()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        configurator = null
        super.onDestroy()
    }
}
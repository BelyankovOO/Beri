package beri.android.modules.MapModule

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import beri.android.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class MapActivity: AppCompatActivity(), MapContract.View, OnMapReadyCallback{
    companion object{
        fun launch(context: Context){
            val intent = Intent(context, MapActivity::class.java)
            context.startActivity(intent)
        }
    }

    var configurator: MapContract.Configurator? = null
    var presenter: MapContract.Presenter? = null
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var categoryButton: ImageButton
    private lateinit var settingButton: ImageButton
    private lateinit var realityButton: ImageButton
    private lateinit var myLocationButton: ImageButton
    private lateinit var profileButton: ImageButton
    private lateinit var promoButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        if(configurator == null) configurator = MapConfigurator()
        configurator?.configurate(this)

        categoryButton = findViewById(R.id.categoryButton)
        settingButton = findViewById(R.id.settingButton)
        realityButton = findViewById(R.id.realityButton)
        myLocationButton = findViewById(R.id.myLocationButton)
        profileButton = findViewById(R.id.profileButton)
        promoButton = findViewById(R.id.promoButton)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //val fragmentAdapter = MapViewPagerAdapter(supportFragmentManager)

        categoryButton.setOnClickListener { presenter?.categoryButtonClicked() }
        settingButton.setOnClickListener { presenter?.settingButtonClicked() }
        realityButton.setOnClickListener { presenter?.realityButtonClicked() }
        myLocationButton.setOnClickListener { getCurrentLocation() }
        profileButton.setOnClickListener { presenter?.profileButtonClicked() }
        promoButton.setOnClickListener { presenter?.promoButtonClicked() }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setMyLocationButtonEnabled(false)
        getCurrentLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        presenter?.requestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun requestLocationPermission() {
        presenter?.requestLocationPermission(this)
    }

    override fun getCurrentLocation() {
        presenter?.getCurrentLocation(this, mMap, fusedLocationClient, this)
    }

    override fun updateMap(location: Location?, mMap: GoogleMap){
        presenter?.updateMap(location, mMap)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this , msg , Toast.LENGTH_LONG).show()
    }

}




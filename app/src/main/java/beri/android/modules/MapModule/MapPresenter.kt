package beri.android.modules.MapModule

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task

class MapPresenter(private var view: MapContract.View): MapContract.Presenter {

    companion object{
        private const val REQUEST_LOCATION = 1
    }

    var router: MapContract.Router? = null

   override fun requestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getCurrentLocation()
            } else {
                view.showInfoMessage("LocationPermissionDenied")
            }
        }
    }

    override fun requestLocationPermission(activity: MapActivity) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
    }

    override fun updateMap(location: Location?, mMap: GoogleMap) {
        if (location != null) {
            val latLng = LatLng(location.latitude, location.longitude)
            val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f)
            mMap.moveCamera(update)
        } else {
            view.showInfoMessage("Location not found")
        }
    }

    override fun getCurrentLocation(context: Context, mMap: GoogleMap, locationClient: FusedLocationProviderClient, activity: MapActivity) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission(activity)
        } else {
            mMap.isMyLocationEnabled = true
            locationClient.lastLocation.addOnCompleteListener {
                updateMap(it.result, mMap)
            }
        }
    }

    override fun settingButtonClicked() {
        router?.navigateToSettings()
    }

    override fun categoryButtonClicked() {
        router?.navigateToCategory()
    }

    override fun realityButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun profileButtonClicked() {
        router?.navigateToProfile()
    }

    override fun promoButtonClicked() {
        router?.navigateToPromo()
    }

}
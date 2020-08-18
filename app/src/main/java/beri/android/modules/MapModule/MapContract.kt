package beri.android.modules.MapModule

import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.tasks.Task

interface MapContract {
    interface View{
        fun showInfoMessage(msg: String)
        fun requestLocationPermission()
        fun getCurrentLocation()
        fun updateMap(location: Location?, mMap: GoogleMap)
    }

    interface Presenter {
        fun requestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
        fun requestLocationPermission(activity: MapActivity)
        fun updateMap(location: Location?, mMap: GoogleMap)
        fun getCurrentLocation(context: Context, mMap: GoogleMap, locationClient: FusedLocationProviderClient, activity: MapActivity)
        fun settingButtonClicked()
        fun categoryButtonClicked()
        fun realityButtonClicked()
        fun profileButtonClicked()
        fun promoButtonClicked()
    }

    interface Interactor{

    }

    interface Router{
        fun navigateToSettings()
        fun navigateToCategory()
        fun navigateToProfile()
        fun navigateToPromo()
    }

    interface Configurator{
        fun configurate(view: MapActivity)
    }
}
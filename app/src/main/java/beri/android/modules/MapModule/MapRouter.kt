package beri.android.modules.MapModule

import beri.android.modules.CategoryModule.CategoryActivity
import beri.android.modules.ProfileModule.ProfileActivity

class MapRouter(private var activity: MapActivity): MapContract.Router {
    override fun navigateToSettings() {
        TODO("Not yet implemented")
    }

    override fun navigateToCategory() {
        CategoryActivity.launch(activity)
    }

    override fun navigateToProfile() {
        ProfileActivity.launch(activity)
    }

    override fun navigateToPromo() {
        TODO("Not yet implemented")
    }

}
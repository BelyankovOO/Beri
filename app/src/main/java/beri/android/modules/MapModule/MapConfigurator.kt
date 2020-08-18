package beri.android.modules.MapModule

class MapConfigurator: MapContract.Configurator {

    override fun configurate(view: MapActivity) {
        val presenter = MapPresenter(view)
        val router = MapRouter(view)

        presenter.router = router
        view.presenter = presenter
    }

}
package kashyap.`in`.androidbestpractices

import android.app.Application
import kashyap.`in`.androidbestpractices.injection.AppComponent
import kashyap.`in`.androidbestpractices.injection.DaggerAppComponent
import kashyap.`in`.androidbestpractices.injection.modules.AppModule

class MyApplication : Application() {

    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
    }

    private fun buildAppComponent() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        component?.inject( this)
    }
}
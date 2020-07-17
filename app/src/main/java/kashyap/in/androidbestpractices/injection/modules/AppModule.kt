package kashyap.`in`.androidbestpractices.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import kashyap.`in`.androidbestpractices.MyApplication
import kashyap.`in`.androidbestpractices.injection.ApplicationContext
import kashyap.`in`.androidbestpractices.injection.ApplicationScope

@Module
class AppModule(var myApp: MyApplication) {

    @ApplicationContext
    @ApplicationScope
    @Provides
    fun getContext(): Context{
        return myApp
    }
}
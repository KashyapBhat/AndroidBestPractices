package kashyap.`in`.androidbestpractices.injection

import android.content.Context
import dagger.Component
import kashyap.`in`.androidbestpractices.MyApplication
import kashyap.`in`.androidbestpractices.injection.modules.AppModule
import kashyap.`in`.androidbestpractices.injection.modules.ViewModelModule
import kashyap.`in`.androidbestpractices.network.MyRepository
import kashyap.`in`.androidbestpractices.ui.RepoDetailsFragment

@ApplicationScope
@Component(modules = [AppModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    @ApplicationContext
    fun getContext(): Context

    fun getRepository(): MyRepository

    fun inject(app: MyApplication)

    fun inject(fragment: RepoDetailsFragment)

}
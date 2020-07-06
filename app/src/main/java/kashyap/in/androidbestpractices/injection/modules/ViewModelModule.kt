package kashyap.`in`.androidbestpractices.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kashyap.`in`.androidbestpractices.injection.ViewModelKey
import kashyap.`in`.androidbestpractices.ui.userdetails.UserDetailsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindRepoDetailsViewModel(userDetailsViewModel: UserDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
package uz.gita.contactworkteam.diimport dagger.Bindsimport dagger.Moduleimport dagger.hilt.InstallInimport dagger.hilt.android.components.ViewModelComponentimport uz.gita.contactworkteam.presenter.screens.main.MainContractimport uz.gita.contactworkteam.presenter.screens.main.MainDirection@Module@InstallIn(ViewModelComponent::class)interface DirectionModule {    @Binds    fun bindsMainDirection(impl: MainDirection): MainContract.MainDirection}
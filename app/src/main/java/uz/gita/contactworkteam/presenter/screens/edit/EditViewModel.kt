package uz.gita.contactworkteam.presenter.screens.editimport androidx.lifecycle.ViewModelimport androidx.lifecycle.viewModelScopeimport dagger.hilt.android.lifecycle.HiltViewModelimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.launchInimport kotlinx.coroutines.launchimport uz.gita.contactworkteam.domain.repository.AppRepositoryimport javax.inject.Inject@HiltViewModelclass EditViewModel @Inject constructor(    private val appRepository: AppRepository,    private val direction: EditContact.EditDirection,) : EditContact.ViewModel, ViewModel() {    override fun onEventDispatcher(intent: EditContact.Intent) {        when (intent) {            is EditContact.Intent.SaveData -> {                viewModelScope.launch(Dispatchers.IO) {                    appRepository.editContact(intent.contactParam).launchIn(viewModelScope)                    direction.openMainScreen()                }            }            EditContact.Intent.Back -> {                viewModelScope.launch {                    direction.openMainScreen()                }            }        }    }}
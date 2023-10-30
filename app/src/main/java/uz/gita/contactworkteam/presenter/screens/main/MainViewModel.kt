package uz.gita.contactworkteam.presenter.screens.mainimport androidx.lifecycle.ViewModelimport androidx.lifecycle.viewModelScopeimport dagger.hilt.android.lifecycle.HiltViewModelimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.MutableStateFlowimport kotlinx.coroutines.flow.launchInimport kotlinx.coroutines.flow.onEachimport kotlinx.coroutines.flow.updateimport kotlinx.coroutines.launchimport uz.gita.contactworkteam.domain.repository.AppRepositoryimport javax.inject.Inject@HiltViewModelclass MainViewModel @Inject constructor(    private val repository: AppRepository,    private val direction: MainContract.MainDirection,) : MainContract.ViewModel, ViewModel() {    override val uiState = MutableStateFlow(MainContract.UIState())    override fun onEventDispatcher(intent: MainContract.Intent) {        when (intent) {            MainContract.Intent.MoveToAdd -> {                viewModelScope.launch {                    direction.openAddScreen()                }            }            MainContract.Intent.Load -> {                loadData()            }            is MainContract.Intent.DeleteContact -> {                viewModelScope.launch(Dispatchers.IO) {                    repository.deleteContact(intent.data).launchIn(viewModelScope)                    loadData()                }            }            is MainContract.Intent.EditContact -> {                viewModelScope.launch(Dispatchers.IO) {                    direction.openEditScreen(intent.data)                }            }        }    }    private fun loadData() {        repository.getAllContacts().onEach {            it.onSuccess {                uiState.update { uiState ->                    uiState.copy(contactsList = it)                }            }            it.onFailure {                //...            }        }.launchIn(viewModelScope)    }}
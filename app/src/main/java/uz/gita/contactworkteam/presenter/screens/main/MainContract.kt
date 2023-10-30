package uz.gita.contactworkteam.presenter.screens.mainimport kotlinx.coroutines.flow.StateFlowimport uz.gita.contactworkteam.domain.models.ContactParaminterface MainContract {    interface ViewModel {        val uiState: StateFlow<UIState>        fun onEventDispatcher(intent: Intent)    }    data class UIState(        val contactsList: List<ContactParam> = emptyList(),    )    interface Intent {        object MoveToAdd : Intent        object Load : Intent        data class DeleteContact(            val data: ContactParam,        ) : Intent        data class EditContact(            val data: ContactParam,        ) : Intent    }    interface MainDirection {        suspend fun openAddScreen()        suspend fun openEditScreen(contactParam: ContactParam)    }}
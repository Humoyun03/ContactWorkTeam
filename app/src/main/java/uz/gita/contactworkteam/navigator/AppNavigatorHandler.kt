package uz.gita.contactworkteam.navigatorimport cafe.adriel.voyager.navigator.Navigatorimport kotlinx.coroutines.flow.SharedFlowtypealias NavigatorArgs = Navigator.() -> Unitinterface AppNavigatorHandler {    val uiNavigator: SharedFlow<NavigatorArgs>}
package uz.gita.contactworkteam.presenter.screens.mainimport android.annotation.SuppressLintimport android.os.Buildimport androidx.annotation.RequiresApiimport androidx.compose.foundation.clickableimport androidx.compose.foundation.layout.Boximport androidx.compose.foundation.layout.Columnimport androidx.compose.foundation.layout.Spacerimport androidx.compose.foundation.layout.fillMaxSizeimport androidx.compose.foundation.layout.fillMaxWidthimport androidx.compose.foundation.layout.heightimport androidx.compose.foundation.layout.paddingimport androidx.compose.foundation.lazy.LazyColumnimport androidx.compose.foundation.lazy.itemsimport androidx.compose.material.icons.Iconsimport androidx.compose.material.icons.filled.Addimport androidx.compose.material.icons.filled.Settingsimport androidx.compose.material3.Buttonimport androidx.compose.material3.Dividerimport androidx.compose.material3.ExperimentalMaterial3Apiimport androidx.compose.material3.FloatingActionButtonimport androidx.compose.material3.Iconimport androidx.compose.material3.IconButtonimport androidx.compose.material3.Textimport androidx.compose.material3.TopAppBarimport androidx.compose.runtime.Composableimport androidx.compose.runtime.Stateimport androidx.compose.runtime.collectAsStateimport androidx.compose.runtime.mutableStateOfimport androidx.compose.ui.Alignmentimport androidx.compose.ui.Modifierimport androidx.compose.ui.graphics.Colorimport androidx.compose.ui.text.TextStyleimport androidx.compose.ui.text.font.FontWeightimport androidx.compose.ui.tooling.preview.Previewimport androidx.compose.ui.unit.dpimport androidx.compose.ui.unit.spimport cafe.adriel.voyager.androidx.AndroidScreenimport cafe.adriel.voyager.hilt.getViewModelimport com.google.accompanist.permissions.ExperimentalPermissionsApiimport com.google.accompanist.permissions.isGrantedimport com.google.accompanist.permissions.rememberPermissionStateimport com.google.accompanist.permissions.shouldShowRationaleimport uz.gita.contactworkteam.presenter.components.ContactItemclass MainScreen : AndroidScreen() {    @RequiresApi(Build.VERSION_CODES.TIRAMISU)    @OptIn(ExperimentalPermissionsApi::class)    @Composable    override fun Content() {        val viewModel: MainContract.ViewModel = getViewModel<MainViewModel>()        viewModel.onEventDispatcher(MainContract.Intent.Load)        val notificationPermissionState =            rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)        if (notificationPermissionState.status.isGranted) {            MainScreenComponent(                uiState = viewModel.uiState.collectAsState(),                onEventDispatcher = viewModel::onEventDispatcher            )        } else {            Box(                modifier = Modifier                    .fillMaxSize(), contentAlignment = Alignment.Center            ) {                val textToShow = if (notificationPermissionState.status.shouldShowRationale) {                    "The notification permission is important for this app. Please grant the permission."                } else {                    "Contacts are not available"                }                Column() {                    Text(textToShow)                    Spacer(modifier = Modifier.height(8.dp))                    Button(onClick = { notificationPermissionState.launchPermissionRequest() }) {                        Text("Request permission")                    }                }            }        }    }}@OptIn(ExperimentalMaterial3Api::class)@Composablefun MainScreenComponent(    uiState: State<MainContract.UIState>,    onEventDispatcher: (MainContract.Intent) -> Unit,) {    Box(modifier = Modifier.fillMaxSize()) {        Column(modifier = Modifier.fillMaxSize()) {            TopAppBar(                title = {                    Text(                        text = "Contacts",                        style = TextStyle(                            fontSize = 20.sp,                            fontWeight = FontWeight(500),                            color = Color(0xFF000000),                        )                    )                },                actions = {                    IconButton(                        onClick = {                            //...                        }                    ) {                        Icon(                            imageVector = Icons.Default.Settings,                            contentDescription = "SETTINGS",                            modifier = Modifier.clickable {                                onEventDispatcher.invoke(MainContract.Intent.MoveToSettings)                            })                    }                },            )            Divider(                modifier = Modifier                    .fillMaxWidth()                    .height(1.dp)            )            LazyColumn {                items(uiState.value.contactsList) { data ->                    ContactItem(                        contactParam = data,                        onDeleteClick = {                            onEventDispatcher.invoke(MainContract.Intent.DeleteContact(it))                        },                        onEditClick = {                            onEventDispatcher.invoke(MainContract.Intent.EditContact(it))                        }                    )                }            }        }        FloatingActionButton(            onClick = { onEventDispatcher.invoke(MainContract.Intent.MoveToAdd) },            modifier = Modifier                .padding(30.dp)                .align(Alignment.BottomEnd),            containerColor = Color(0xFF00B2FF)        ) {            Icon(                imageVector = Icons.Default.Add,                contentDescription = "ADD",                tint = Color.White            )        }    }}@SuppressLint("UnrememberedMutableState")@Composable@Preview(showBackground = true)fun MainScreenPreview() {    MainScreenComponent(uiState = mutableStateOf(MainContract.UIState()), onEventDispatcher = {})}
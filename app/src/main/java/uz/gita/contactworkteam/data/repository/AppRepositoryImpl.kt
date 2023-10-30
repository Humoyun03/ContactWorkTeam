package uz.gita.contactworkteam.data.repositoryimport android.util.Logimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.Flowimport kotlinx.coroutines.flow.catchimport kotlinx.coroutines.flow.flowimport kotlinx.coroutines.flow.flowOnimport uz.gita.contactworkteam.data.mappers.toDataimport uz.gita.contactworkteam.data.mappers.toEntityimport uz.gita.contactworkteam.data.models.ContactParamimport uz.gita.contactworkteam.data.source.local.room.dao.ContactDaoimport uz.gita.contactworkteam.domain.repository.AppRepositoryimport javax.inject.Injectclass AppRepositoryImpl @Inject constructor(    private val contactDao: ContactDao,) : AppRepository {    override fun getAllContacts(): Flow<Result<List<ContactParam>>> = flow {        Log.d("TTT", "getAllContacts: ")        emit(Result.success(contactDao.getAllContacts().map { it.toData() }))    }        .catch { emit(Result.failure(it)) }        .flowOn(Dispatchers.IO)    override fun addContact(contactParam: ContactParam): Flow<Result<Unit>> = flow {        Log.d("TTT", "addContact: $contactParam")        emit(Result.success(contactDao.addContact(contactParam.toEntity())))    }        .catch { emit(Result.failure(it)) }        .flowOn(Dispatchers.IO)    override fun editContact(contactParam: ContactParam): Flow<Result<Unit>> = flow {        emit(Result.success(contactDao.updateContact(contactParam.toEntity())))    }        .catch { emit(Result.failure(it)) }        .flowOn(Dispatchers.IO)    override fun deleteContact(contactParam: ContactParam): Flow<Result<Unit>> = flow {        emit(Result.success(contactDao.deleteContact(contactParam.toEntity())))    }        .catch { emit(Result.failure(it)) }        .flowOn(Dispatchers.IO)}
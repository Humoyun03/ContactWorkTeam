package uz.gita.contactworkteam.data.source.local.room.daoimport androidx.room.Daoimport androidx.room.Deleteimport androidx.room.Queryimport androidx.room.Updateimport androidx.room.Upsertimport kotlinx.coroutines.flow.Flowimport uz.gita.contactworkteam.data.source.local.room.entity.ContactEntity@Daointerface ContactDao {    @Query("SELECT * FROM ContactEntity")    fun getAllContacts(): Flow<List<ContactEntity>>    @Upsert    fun addContact(contactEntity: ContactEntity)    @Delete    fun deleteContact(contactEntity: ContactEntity)    @Update    fun updateContact(contactEntity: ContactEntity)}
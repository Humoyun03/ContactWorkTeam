package uz.gita.contactworkteam.data.source.local.room.daoimport androidx.room.Daoimport androidx.room.Deleteimport androidx.room.Queryimport androidx.room.Updateimport androidx.room.Upsertimport uz.gita.contactworkteam.data.source.local.room.entity.ContactEntity@Daointerface ContactDao {    @Query("SELECT * FROM ContactEntity")    suspend fun getAllContacts(): List<ContactEntity>    @Upsert    fun addContact(contactEntity: ContactEntity)    @Delete    fun deleteContact(contactEntity: ContactEntity)    @Update    fun updateContact(contactEntity: ContactEntity)    @Query("SELECT * FROM ContactEntity where isSend==0")    suspend fun getUnSendContacts(): List<ContactEntity>}
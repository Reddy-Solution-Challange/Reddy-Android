package com.soopeach.reddy.data.model

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

object FirebaseStorageManager {

    private val storage
        get() = Firebase.storage.reference.child("images/${System.currentTimeMillis()}.jpg")

    suspend fun uploadImage(byteArray: ByteArray): String {
        val task = storage.putBytes(byteArray).await()
        return task.storage.downloadUrl.await().toString()
    }

}
package com.ms.daelimtime.util

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

object DBHelper {
    val database: DatabaseReference = Firebase.database.reference //레퍼런스 초기화
    var email: String? = ""
    var name: String? = ""
    var profile_Src: String? = ""
    var id: Long? = 0

    fun getUSerInfo(userId: Long?) {
        database.child("User").child("UID_${userId}").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }.toString()
    }

    fun sendSchoolSurvey(title: String, doc: String, type: String) {
        database.child("School_Survey").child("SC_${id}").child("title").setValue(title)
        database.child("School_Survey").child("SC_${id}").child("doc").setValue(doc)
        database.child("School_Survey").child("SC_${id}").child("type").setValue(type)

    }

    fun sendStudentSurvey(title: String, doc: String, type: String) {

    }
}

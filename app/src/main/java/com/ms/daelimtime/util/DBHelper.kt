package com.ms.daelimtime.util

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import retrofit2.http.POST
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object DBHelper {
    val database: DatabaseReference = Firebase.database.reference //레퍼런스 초기화
    var email: String? = ""
    var name: String? = ""
    var profile_Src: String? = ""
    var id: Long? = 0
    var userClassNum : String? = ""
    var userClass : String? = ""

    fun getUSerInfo(userId: Long?) {
        database.child("User").child("UID_${id}").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }.toString()
    }

    fun sendSchoolSurvey(title: String, doc: String, type: String) {
        database.child("School_Survey").child("SC_${id}_${title}").child("title").setValue(title)
        database.child("School_Survey").child("SC_${id}_${title}").child("doc").setValue(doc)
        database.child("School_Survey").child("SC_${id}_${title}").child("type").setValue(type)

    }

    fun sendStudentSurvey(title: String, doc: String, type: String) {
        database.child("Student_Survey").child("ST_${id}_${title}").child("title").setValue(title)
        database.child("Student_Survey").child("ST_${id}_${title}").child("doc").setValue(doc)
        database.child("Student_Survey").child("ST_${id}_${title}").child("type").setValue(type)
    }
    fun getSchoolSurveyList(){
        val allDatabase = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val post = datasnapshot.child("School_Survey").children.forEach {
                    Log.d("데이터베이스1", "${it.key} === ${it.getValue().toString()}")
                }
            }

            override fun onCancelled(datasnapshot: DatabaseError) {
                Log.d("데이터베이스 에러", "ERROR")
            }
        }
        database.addValueEventListener(allDatabase)
    }

    fun sendUserData(userClass : String, userClassNum: String){
        database.child("User").child("UID_${id}").child("userID").setValue(id)
        database.child("User").child("UID_${id}").child("userClass").setValue(userClass)
        database.child("User").child("UID_${id}").child("userClassNum").setValue(userClassNum)
    }
}

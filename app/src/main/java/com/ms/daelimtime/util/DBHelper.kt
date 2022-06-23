package com.ms.daelimtime.util

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.user.model.User
import com.ms.daelimtime.fragment.UserInfo_Fragment
import com.ms.daelimtime.util.DBHelper.getUserData
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

    //School_Survey 데이터 Map
    var school_List_Key: ArrayList<String> = ArrayList()
    var school_Title_List: HashMap<String?, Any> = HashMap()
    var school_Doc_List: HashMap<String?, Any> = HashMap()
    var school_Type_List: HashMap<String?, Any> = HashMap()

    //Student_Survey 데이터 Map
    var student_List_Key: ArrayList<String> = ArrayList()
    var student_Title_List: HashMap<String?, Any> = HashMap()
    var student_Doc_List: HashMap<String?, Any> = HashMap()
    var student_Type_List: HashMap<String?, Any> = HashMap()

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

    fun getSurveyList() {
        val allDatabase = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                //School Map GetData
                datasnapshot.child("School_Survey").children.forEach {
                    school_List_Key.add(it.key!!)
                    school_Title_List.put(it.key, it.child("title").getValue().toString())
                    school_Doc_List.put(it.key, it.child("doc").getValue().toString())
                    school_Type_List.put(it.key, it.child("type").getValue().toString())
                }

                //Student Map GetData
                datasnapshot.child("Student_Survey").children.forEach {
                    student_List_Key.add(it.key!!)
                    student_Title_List.put(it.key, it.child("title").getValue().toString())
                    student_Doc_List.put(it.key, it.child("doc").getValue().toString())
                    student_Type_List.put(it.key, it.child("type").getValue().toString())
                }

                //User 추가


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

    fun getUserData(){
        val userDataListener = object : ValueEventListener{
            var user_classNum : String = ""
            override fun onDataChange(snapshot: DataSnapshot) {
                var user_class : String = database.child("User").child("0").child("userClass").get().toString()
                UserInfo_Fragment().getUserData(user_class)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w( "loadPost:onCancelled", "error")
            }
        }
        database.addValueEventListener(userDataListener)
    }
}

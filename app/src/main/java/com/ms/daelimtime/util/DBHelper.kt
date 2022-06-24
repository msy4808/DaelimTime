package com.ms.daelimtime.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ms.daelimtime.fragment.UserInfo_Fragment
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object DBHelper {
    val TAG: String = "로그"

    val database: DatabaseReference = Firebase.database.reference //레퍼런스 초기화
    var email: String? = ""
    var name: String? = ""
    var profile_Src: String? = ""
    var id: Long? = 0
    var userClassNum : String? = ""
    var userClass : String? = ""
    var userNickName : String? = ""

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

    fun sendSchoolSurvey(title: String, doc: String, type: String) {
        database.child("School_Survey").child("SC_${title}").child("title").setValue(title)
        database.child("School_Survey").child("SC_${title}").child("doc").setValue(doc)
        database.child("School_Survey").child("SC_${title}").child("type").setValue(type)
        database.child("School_Survey").child("SC_${title}").child("id").setValue(id)


    }

    fun sendStudentSurvey(title: String, doc: String, type: String) {
        database.child("Student_Survey").child("ST_${title}").child("title").setValue(title)
        database.child("Student_Survey").child("ST_${title}").child("doc").setValue(doc)
        database.child("Student_Survey").child("ST_${title}").child("type").setValue(type)
        database.child("Student_Survey").child("ST_${title}").child("id").setValue(id)
    }

    fun getSurveyList() {
        val allDatabase = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                Log.d(TAG, "DBHelper - getSurveyList() called")
                school_List_Key.clear()
                student_List_Key.clear()
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

                //User 불러오기
                //학과
                database.child("User").child("UID_${id}").child("userClass").get().addOnSuccessListener {
                    userClass = it.value.toString()

                } .addOnFailureListener {

                }
                //학번
                database.child("User").child("UID_${id}").child("userClassNum").get().addOnSuccessListener {
                    userClassNum = it.value.toString()

                } .addOnFailureListener {
                    Log.e("DBHelper","학번 가져오기 오류")
                }
                //닉네임
                database.child("User").child("UID_${id}").child("userNickName").get().addOnSuccessListener {
                    userNickName = it.value.toString()

                } .addOnFailureListener {
                    Log.e("DBHelper","닉네임 가져오기 오류")
                }


            }
            override fun onCancelled(datasnapshot: DatabaseError) {
                Log.d("데이터베이스 에러", "ERROR")
            }
        }
        database.addValueEventListener(allDatabase)
    }

    fun sendUserData(userNickName : String,userClass : String, userClassNum: String){

        database.child("User").child("UID_${id}").child("userClass").setValue(userClass)
        database.child("User").child("UID_${id}").child("userClassNum").setValue(userClassNum)
        database.child("User").child("UID_${id}").child("userNickName").setValue(userNickName)
    }


}

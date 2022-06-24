package com.ms.daelimtime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ms.daelimtime.R
import com.ms.daelimtime.fragment.Survey_Type_A
import com.ms.daelimtime.fragment.Survey_Type_B
import com.ms.daelimtime.util.DBHelper

class SurveyPage : AppCompatActivity() {

    val TAG: String = "로그"
    var clickTitle: String? = null
    var clickDoc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_page)
        Log.d(TAG, "SurveyPage - onCreate() called")
        clickTitle = intent.getStringExtra("title")
        clickDoc = intent.getStringExtra("doc")
        checkType()
    }

    fun checkType() {
        DBHelper.database.child("School_Survey").child("SC_${clickTitle}").child("type").get()
            .addOnSuccessListener {
                if (it.value == "A") {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.survey_ly,
                        Survey_Type_A().apply {  //프래그먼트에서 데이터 전달은 arguments를 사용
                            arguments = Bundle().apply {
                                putString("table", "School_Survey")
                                putString("title", "SC_${clickTitle}")
                            }
                        }).commit()

                } else if (it.value == "B") {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.survey_ly,
                        Survey_Type_B().apply {  //프래그먼트에서 데이터 전달은 arguments를 사용
                            arguments = Bundle().apply {
                                putString("table", "School_Survey")
                                putString("title", "SC_${clickTitle}")
                            }
                        }).commit()
                } else {
                    DBHelper.database.child("Student_Survey").child("ST_${clickTitle}").child("type")
                        .get().addOnSuccessListener {
                        if (it.value == "A") {
                            supportFragmentManager.beginTransaction().replace(
                                R.id.survey_ly,
                                Survey_Type_A().apply {  //프래그먼트에서 데이터 전달은 arguments를 사용
                                    arguments = Bundle().apply {
                                        putString("table", "Student_Survey")
                                        putString("title", "ST_${clickTitle}")
                                    }
                                }).commit()
                        } else if (it.value == "B") {
                            supportFragmentManager.beginTransaction().replace(
                                R.id.survey_ly,
                                Survey_Type_B().apply {  //프래그먼트에서 데이터 전달은 arguments를 사용
                                    arguments = Bundle().apply {
                                        putString("table", "Student_Survey")
                                        putString("title", "ST_${clickTitle}")
                                    }
                                }).commit()
                        }
                    }.addOnFailureListener {
                        Log.d("데이터 검사", "없음")
                    }
                }
            }
    }
}
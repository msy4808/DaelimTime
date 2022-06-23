package com.ms.daelimtime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContentProviderCompat
import com.ms.daelimtime.R
import com.ms.daelimtime.util.DBHelper

class SurveyEditActivity : AppCompatActivity() {
    lateinit var edit_Title: EditText
    lateinit var edit_Doc: EditText
    lateinit var typeSpinner: Spinner
    lateinit var type: String
    var selectType:String = "A"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit)
        val sendBtn:Button = findViewById(R.id.send_Btn)
        type = intent.getStringExtra("type").toString()
        typeSpinner = findViewById<Spinner>(R.id.survey_Type)
        edit_Title = findViewById(R.id.survey_Title)
        edit_Doc = findViewById(R.id.survey_Doc)
        typeSpinner.adapter = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.surveyList,
            android.R.layout.simple_spinner_item
        )
        typeSpinner.setSelection(0)
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 -> {
                        selectType = "A"
                    }
                    1 -> {
                        selectType = "B"
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        sendBtn.setOnClickListener {
            if(edit_Title.length() == 0 || edit_Doc.length() == 0){
                Toast.makeText(applicationContext, "내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else {
                setData()
            }
        }
    }

    private fun setData() {
        val title = edit_Title.text.toString()
        val doc = edit_Doc.text.toString()

        if(type =="school") {
            DBHelper.sendSchoolSurvey(title, doc, selectType)
            finish()
        }else{
            DBHelper.sendStudentSurvey(title, doc, selectType)
            finish()
        }
    }
}
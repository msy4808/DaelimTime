package com.ms.daelimtime.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.SurveyPage
import com.ms.daelimtime.util.DBHelper

class Survey_Type_A : Fragment() {
    val TAG: String = "로그"

    var result: String = ""
    var title: String = ""
    var table: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_survey_type_a, container, false)
        val titleView = view.findViewById<TextView>(R.id.type_A_Title)
        val docView = view.findViewById<TextView>(R.id.type_A_Doc)

        val group = view.findViewById<RadioGroup>(R.id.a_Group)
        val btn = view.findViewById<Button>(R.id.success_Btn_A)
        arguments?.getString("title")?.let { //arauments로 전달한 데이터 확인
            title = it
        }
        arguments?.getString("table")?.let { //arauments로 전달한 데이터 확인
            table = it
        }

        DBHelper.database.child(table).child(title).get().addOnSuccessListener {
            titleView.text = it.child("title").value.toString()
            docView.text = it.child("doc").value.toString()
        }.addOnFailureListener{
        }

        group.setOnCheckedChangeListener {group, itemId ->
            when(itemId) {
                R.id.a_Btn_1 -> {
                    result = "찬성"
                }
                R.id.a_Btn_2 -> {
                    result = "반대"
                }
            }
        }

        btn.setOnClickListener {
            checkUserSelect()
            (activity as SurveyPage).finish()
        }
        return view
    }
    fun checkUserSelect(){ //사용자가 선택한 버튼 값 검사 후 데이터 update
        DBHelper.database.child("Result").child(title).get().addOnSuccessListener {
            if(it.value == null && result == "찬성") {
                DBHelper.database.child("Result").child(title).child("agree").setValue(1)
                DBHelper.database.child("Result").child(title).child("oppose").setValue(0)
            }else if(it.value == null && result == "반대"){
                DBHelper.database.child("Result").child(title).child("agree").setValue(0)
                DBHelper.database.child("Result").child(title).child("oppose").setValue(1)
            }else{
                if(result == "찬성") {
                    DBHelper.database.child("Result").child(title).child("agree").get().addOnSuccessListener {
                        val i = Integer.parseInt(it.value.toString())
                        DBHelper.database.child("Result").child(title).child("agree").setValue(i+1)
                    }
                }else{
                    DBHelper.database.child("Result").child(title).child("oppose").get().addOnSuccessListener {
                        val i = Integer.parseInt(it.value.toString())
                        DBHelper.database.child("Result").child(title).child("oppose").setValue(i+1)
                    }
                }
            }
        }
    }
}
package com.ms.daelimtime.fragment

import android.os.Bundle
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

class Survey_Type_B : Fragment() {
    private var result: String = "Middle"
    private var title: String = ""
    private var table: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_survey_type_b, container, false)
        val titleView = view.findViewById<TextView>(R.id.type_B_Title)
        val docView = view.findViewById<TextView>(R.id.type_B_Doc)

        val group = view.findViewById<RadioGroup>(R.id.b_Group)
        val btn = view.findViewById<Button>(R.id.success_Btn_B)
        group.check(R.id.b_Btn_3)
        arguments?.getString("title")?.let { //arauments로 전달한 데이터 확인
            title = it
        }
        arguments?.getString("table")?.let { //arauments로 전달한 데이터 확인
            table = it
        }

        DBHelper.database.child(table).child(title).get().addOnSuccessListener {
            titleView.text = it.child("title").value.toString()
            docView.text = it.child("doc").value.toString()
        }.addOnFailureListener {
        }

        group.setOnCheckedChangeListener { group, itemId ->
            when (itemId) {
                R.id.b_Btn_1 -> {
                    result = "Very Good"
                }
                R.id.b_Btn_2 -> {
                    result = "Good"
                }
                R.id.b_Btn_3 -> {
                    result = "Middle"
                }
                R.id.b_Btn_4 -> {
                    result = "Bad"
                }
                R.id.b_Btn_5 -> {
                    result = "Very Bad"
                }
            }
        }

        btn.setOnClickListener {
            checkUserSelect()
            (activity as SurveyPage).finish()
        }
        return view
    }

    private fun checkUserSelect() { //사용자가 선택한 버튼 값 검사 후 데이터 update
        DBHelper.database.child("Result").child(title).get().addOnSuccessListener {
            if (it.value == null && result == "Very Good") {
                DBHelper.database.child("Result").child(title).child("verygood").setValue(1)
                DBHelper.database.child("Result").child(title).child("good").setValue(0)
                DBHelper.database.child("Result").child(title).child("middle").setValue(0)
                DBHelper.database.child("Result").child(title).child("bad").setValue(0)
                DBHelper.database.child("Result").child(title).child("verybad").setValue(0)
                DBHelper.database.child("Result").child(title).child("PC")
                    .child("UID_${DBHelper.id}").setValue(1)
            } else if (it.value == null && result == "Good") {
                DBHelper.database.child("Result").child(title).child("verygood").setValue(0)
                DBHelper.database.child("Result").child(title).child("good").setValue(1)
                DBHelper.database.child("Result").child(title).child("middle").setValue(0)
                DBHelper.database.child("Result").child(title).child("bad").setValue(0)
                DBHelper.database.child("Result").child(title).child("verybad").setValue(0)
                DBHelper.database.child("Result").child(title).child("PC")
                    .child("UID_${DBHelper.id}").setValue(1)
            } else if (it.value == null && result == "Middle") {
                DBHelper.database.child("Result").child(title).child("verygood").setValue(0)
                DBHelper.database.child("Result").child(title).child("good").setValue(0)
                DBHelper.database.child("Result").child(title).child("middle").setValue(1)
                DBHelper.database.child("Result").child(title).child("bad").setValue(0)
                DBHelper.database.child("Result").child(title).child("verybad").setValue(0)
                DBHelper.database.child("Result").child(title).child("PC")
                    .child("UID_${DBHelper.id}").setValue(1)
            } else if (it.value == null && result == "Bad") {
                DBHelper.database.child("Result").child(title).child("verygood").setValue(0)
                DBHelper.database.child("Result").child(title).child("good").setValue(0)
                DBHelper.database.child("Result").child(title).child("middle").setValue(0)
                DBHelper.database.child("Result").child(title).child("bad").setValue(1)
                DBHelper.database.child("Result").child(title).child("verybad").setValue(0)
                DBHelper.database.child("Result").child(title).child("PC")
                    .child("UID_${DBHelper.id}").setValue(1)
            } else if (it.value == null && result == "Very Bad") {
                DBHelper.database.child("Result").child(title).child("verygood").setValue(0)
                DBHelper.database.child("Result").child(title).child("good").setValue(0)
                DBHelper.database.child("Result").child(title).child("middle").setValue(0)
                DBHelper.database.child("Result").child(title).child("bad").setValue(0)
                DBHelper.database.child("Result").child(title).child("verybad").setValue(1)
                DBHelper.database.child("Result").child(title).child("PC")
                    .child("UID_${DBHelper.id}").setValue(1)
            } else {
                if (result == "Very Good") {
                    DBHelper.database.child("Result").child(title).child("verygood").get()
                        .addOnSuccessListener {
                            val i = Integer.parseInt(it.value.toString())
                            DBHelper.database.child("Result").child(title).child("verygood")
                                .setValue(i + 1)
                            DBHelper.database.child("Result").child(title).child("PC")
                                .child("UID_${DBHelper.id}").setValue(1)
                        }
                } else if (result == "Good") {
                    com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                        .child("good").get().addOnSuccessListener {
                            val i = java.lang.Integer.parseInt(it.value.toString())
                            com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                                .child("good").setValue(i + 1)
                            DBHelper.database.child("Result").child(title).child("PC")
                                .child("UID_${DBHelper.id}").setValue(1)
                        }
                } else if (result == "Middle") {
                    com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                        .child("middle").get().addOnSuccessListener {
                            val i = java.lang.Integer.parseInt(it.value.toString())
                            com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                                .child("middle").setValue(i + 1)
                            DBHelper.database.child("Result").child(title).child("PC")
                                .child("UID_${DBHelper.id}").setValue(1)
                        }
                } else if (result == "Bad") {
                    com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                        .child("bad").get().addOnSuccessListener {
                            val i = java.lang.Integer.parseInt(it.value.toString())
                            com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                                .child("bad").setValue(i + 1)
                            DBHelper.database.child("Result").child(title).child("PC")
                                .child("UID_${DBHelper.id}").setValue(1)
                        }
                } else if (result == "Very Bad") {
                    com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                        .child("verybad").get().addOnSuccessListener {
                            val i = java.lang.Integer.parseInt(it.value.toString())
                            com.ms.daelimtime.util.DBHelper.database.child("Result").child(title)
                                .child("verybad").setValue(i + 1)
                            DBHelper.database.child("Result").child(title).child("PC")
                                .child("UID_${DBHelper.id}").setValue(1)
                        }
                }
            }
        }
    }
}
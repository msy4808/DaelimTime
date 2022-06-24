package com.ms.daelimtime.adapter

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R
import com.ms.daelimtime.util.DBHelper

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val TAG: String = "로그"

    val cardTitle = itemView.findViewById<TextView>(R.id.title)
    val cardDoc = itemView.findViewById<TextView>(R.id.doc)
    val cardEditor = itemView.findViewById<TextView>(R.id.editor)
    val del_Btn = itemView.findViewById<Button>(R.id.del_Btn)

    //기본생성자
    init {
        Log.d(TAG, "ViewHolder - init() called")
    }

    //데이터와 뷰를 묶는다
    fun bind(model: SurveyModel) {
        Log.d(TAG, "ViewHolder - bind() called")
        getEditor(model.title)
        cardTitle.text = model.title
        cardDoc.text = model.doc
    }

    fun getEditor(title: String) {
        DBHelper.database.child("School_Survey").child("SC_${title}").child("id").get()
            .addOnSuccessListener {
                when (it.value) {
                    null -> {
                        DBHelper.database.child("Student_Survey").child("ST_${title}").child("id")
                            .get().addOnSuccessListener {
                            val id = it.value
                            DBHelper.database.child("User").child("UID_${id}").child("userNickName")
                                .get().addOnSuccessListener {
                                cardEditor.text = "작성자 : ${it.value.toString()}"
                                if (id == DBHelper.id) {
                                    del_Btn.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                    else -> {
                        val id = it.value
                        DBHelper.database.child("User").child("UID_${id}").child("userNickName")
                            .get().addOnSuccessListener {
                                cardEditor.text = "작성자 : ${it.value.toString()}"
                                if (id == DBHelper.id) {
                                del_Btn.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
    }
}
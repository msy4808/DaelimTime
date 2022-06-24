package com.ms.daelimtime.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val TAG: String = "로그"

    val cardTitle = itemView.findViewById<TextView>(R.id.title)
    val cardDoc = itemView.findViewById<TextView>(R.id.doc)
    //기본생성자
    init {
        Log.d(TAG, "ViewHolder - init() called")
    }

    //데이터와 뷰를 묶는다
    fun bind(model: SurveyModel){
        Log.d(TAG, "ViewHolder - bind() called")
        cardTitle.text = model.title
        cardDoc.text = model.doc
    }
}
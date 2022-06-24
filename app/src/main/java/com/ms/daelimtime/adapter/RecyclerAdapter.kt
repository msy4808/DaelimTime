package com.ms.daelimtime.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R

class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>(){

    val TAG: String = "로그"

    private var modelList = ArrayList<SurveyModel>()

    //뷰올더가 생성 되었을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_survey, parent, false))
    }

    //뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "RecyclerAdapter - onBindViewHolder() called / position : $position")
        holder.bind(this.modelList[position])
    }

    //목록의 아이템 수
    override fun getItemCount(): Int {
        return this.modelList.size
    }

    //외부에서 데이터 넘기기
    fun submitList(modelList: ArrayList<SurveyModel>){
        this.modelList = modelList
    }
}
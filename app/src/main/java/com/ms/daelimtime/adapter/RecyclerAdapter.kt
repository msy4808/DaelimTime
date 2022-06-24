package com.ms.daelimtime.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.SurveyPage
import com.ms.daelimtime.util.DBHelper

class RecyclerAdapter(val context: Context?) : RecyclerView.Adapter<ViewHolder>() {

    val TAG: String = "로그"

    private var modelList = ArrayList<SurveyModel>()
    lateinit var title: CharSequence
    lateinit var doc: CharSequence
    //뷰올더가 생성 되었을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_survey, parent, false)
        )
    }

    //뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "RecyclerAdapter - onBindViewHolder() called / position : $position")
        holder.bind(this.modelList[position])
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                //클릭 이벤트
                title = holder.cardTitle.text
                doc = holder.cardDoc.text
                checkData()
            }
        }
    }

    //목록의 아이템 수
    override fun getItemCount(): Int {
        return this.modelList.size
    }

    //외부에서 데이터 넘기기
    fun submitList(modelList: ArrayList<SurveyModel>) {
        this.modelList = modelList
    }
    fun checkData(){
        //중복검사
        DBHelper.database.child("Result").child("SC_${title}").child("PC")
            .child("UID_${DBHelper.id}").get().addOnSuccessListener {
                when (it.value) {
                    null -> {
                        DBHelper.database.child("Result").child("ST_${title}").child("PC")
                            .child("UID_${DBHelper.id}").get().addOnSuccessListener {
                                when (it.value) {
                                    null -> {
                                        val intent = Intent(context, SurveyPage::class.java)

                                        intent.putExtra("title", title)
                                        intent.putExtra("doc", doc)
                                        if (context != null) {
                                            context.startActivity(intent)
                                        }
                                    }
                                    else -> {
                                        Toast.makeText(
                                            context,
                                            "이미 참여한 설문 입니다",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                    }
                    else -> {
                        Toast.makeText(context, "이미 참여한 설문 입니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}
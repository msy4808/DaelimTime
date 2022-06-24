package com.ms.daelimtime.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.Result_A_Activity
import com.ms.daelimtime.activity.Result_B_Activity
import com.ms.daelimtime.activity.SurveyPage
import com.ms.daelimtime.util.DBHelper

class RecyclerAdapter(val context: Context?) : RecyclerView.Adapter<ViewHolder>() {

    val TAG: String = "로그"
    private var type = ""
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
                                        if (context != null) { //인탠트 null 검사
                                            context.startActivity(intent)
                                        }
                                    }
                                    else -> { //참여한 설문이면 ResultActivity로 넘겨주기
                                        DBHelper.database.child("Student_Survey").child("ST_${title}").child("type").get().addOnSuccessListener {
                                            when(it.value) { //A설문 일경우 Result A 로 이동
                                                "A" -> {
                                                    val intent = Intent(context, Result_A_Activity::class.java)
                                                    intent.putExtra("title", title)
                                                    intent.putExtra("doc", doc)
                                                    if (context != null) { //인탠트 null 검사
                                                        context.startActivity(intent)
                                                    }
                                                }
                                                else -> {
                                                    //B로 이동
                                                    val intent = Intent(context, Result_B_Activity::class.java)
                                                    intent.putExtra("title", title)
                                                    intent.putExtra("doc", doc)
                                                    if (context != null) { //인탠트 null 검사
                                                        context.startActivity(intent)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                    }
                    else -> { //참여한 설문이면 ResultActivity로 넘겨주기
                        DBHelper.database.child("School_Survey").child("SC_${title}").child("type").get().addOnSuccessListener {
                            when(it.value) { //A설문 일경우 Result A 로 이동
                                "A" -> {
                                    val intent = Intent(context, Result_A_Activity::class.java)
                                    intent.putExtra("title", title)
                                    intent.putExtra("doc", doc)
                                    if (context != null) { //인탠트 null 검사
                                        context.startActivity(intent)
                                    }
                                }
                                else -> {
                                    //B로 이동
                                    val intent = Intent(context, Result_B_Activity::class.java)
                                    intent.putExtra("title", title)
                                    intent.putExtra("doc", doc)
                                    if (context != null) { //인탠트 null 검사
                                        context.startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}
package com.ms.daelimtime.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.MainActivity
import com.ms.daelimtime.activity.SurveyEditActivity
import com.ms.daelimtime.adapter.RecyclerAdapter
import com.ms.daelimtime.adapter.SurveyModel
import com.ms.daelimtime.util.DBHelper


class Sch_SurveyFragment : Fragment() {

    val TAG: String = "로그"

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var key: String

    override fun onStart() {
        super.onStart()
        setRecyclerView()
        Log.d(TAG, "Sch_SurveyFragment - onStart() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sch_survey, container, false)
        Log.d(TAG, "Sch_SurveyFragment - onCreateView() called")
        recyclerView = view.findViewById<RecyclerView>(R.id.school_Recycle)
        val fab = view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { //관리자일 경우 설문 작성
            if(DBHelper.id == 2304993869) {
                val intent:Intent = Intent((activity as MainActivity).applicationContext, SurveyEditActivity::class.java)
                intent.putExtra("type", "school")
                startActivity(intent)
            } else {
                Toast.makeText((activity as MainActivity).applicationContext, "권한이 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun setRecyclerView(){
        //리사이클뷰
        Log.d(TAG, "Sch_SurveyFragment - setRecyclerView() called")
        val modelList = ArrayList<SurveyModel>()

        for (i in 0 .. DBHelper.school_List_Key.size - 1) {
            key = DBHelper.school_List_Key.get(i)
            val model = SurveyModel(DBHelper.school_Title_List.get(key).toString(), DBHelper.school_Doc_List.get(key).toString())
            modelList.add(model)
        }

        //어댑터 인스턴스 생성
        recyclerAdapter = RecyclerAdapter()
        recyclerAdapter.submitList(modelList)

        //리사이클뷰 설정
        recyclerView.apply {
            layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext, LinearLayoutManager.VERTICAL, false)
            //어댑터 장착
            adapter = recyclerAdapter
        }
    }
}
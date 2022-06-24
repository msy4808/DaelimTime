package com.ms.daelimtime.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.MainActivity
import com.ms.daelimtime.activity.SurveyEditActivity
import com.ms.daelimtime.adapter.RecyclerAdapter
import com.ms.daelimtime.adapter.SurveyModel
import com.ms.daelimtime.util.DBHelper

class Stu_SurveyFragment : Fragment() {
    val TAG: String = "로그"

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var key: String

    override fun onStart() {
        super.onStart()
        setRecyclerView()
        Log.d(TAG, "Stu_SurveyFragment - onStart() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stu_survey, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.student_Recycle)

        val fab =
            view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
                R.id.fab
            )

        fab.setOnClickListener { //설문작성 페이지로 넘어감
            val intent: Intent = Intent(
                (activity as MainActivity).applicationContext,
                SurveyEditActivity::class.java
            )
            intent.putExtra("type", "student")
            startActivity(intent)
        }
        return view
    }

    private fun setRecyclerView(){
        //리사이클뷰
        Log.d(TAG, "Stu_SurveyFragment - setRecyclerView() called")
        val modelList = ArrayList<SurveyModel>()

        for (i in 0 .. DBHelper.student_List_Key.size - 1) {
            key = DBHelper.student_List_Key.get(i)
            val model = SurveyModel(DBHelper.student_Title_List.get(key).toString(), DBHelper.student_Doc_List.get(key).toString())
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
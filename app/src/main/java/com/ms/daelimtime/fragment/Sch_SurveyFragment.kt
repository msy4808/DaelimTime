package com.ms.daelimtime.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.MainActivity
import com.ms.daelimtime.activity.SurveyEditActivity
import com.ms.daelimtime.util.DBHelper

class Sch_SurveyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sch_survey, container, false)
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
}
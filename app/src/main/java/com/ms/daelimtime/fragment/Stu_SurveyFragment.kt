package com.ms.daelimtime.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.ms.daelimtime.R
import com.ms.daelimtime.activity.MainActivity
import com.ms.daelimtime.activity.SurveyEditActivity
import com.ms.daelimtime.util.DBHelper

class Stu_SurveyFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stu_survey, container, false)
        val fab =
            view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
                R.id.fab
            )

        fab.setOnClickListener { //관리자일 경우 설문 작성
            val intent: Intent = Intent(
                (activity as MainActivity).applicationContext,
                SurveyEditActivity::class.java
            )
            intent.putExtra("type", "student")
            startActivity(intent)
        }
        return view
    }


}
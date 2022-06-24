package com.ms.daelimtime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ms.daelimtime.R
import com.ms.daelimtime.fragment.Notice_Fragment
import com.ms.daelimtime.fragment.Sch_SurveyFragment
import com.ms.daelimtime.fragment.Stu_SurveyFragment
import com.ms.daelimtime.fragment.UserInfo_Fragment
import com.ms.daelimtime.util.DBHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val bottomNavi = findViewById<BottomNavigationView>(R.id.bottomNavi)
        supportFragmentManager.beginTransaction().replace(R.id.home_ly, Sch_SurveyFragment()).commit() //초기화면 홈 프래그먼트로 지정
        bottomNavi.setOnNavigationItemSelectedListener { item -> //바텀네비 메뉴를 클릭하면 해당하는 프래그먼트로 전환
            when(item.itemId) {
                R.id.menu1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.home_ly, Sch_SurveyFragment()).commit()
                    true
                }
                R.id.menu2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.home_ly, Stu_SurveyFragment()).commit()
                    true
                }
                R.id.menu3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.home_ly, Notice_Fragment()).commit()
                    true
                }
                R.id.menu4 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.home_ly, UserInfo_Fragment()).commit()
                    true
                }
                else -> false
            }
        }

    }
}
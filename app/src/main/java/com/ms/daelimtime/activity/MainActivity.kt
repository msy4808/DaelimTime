package com.ms.daelimtime.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ms.daelimtime.R
import com.ms.daelimtime.fragment.Notice_Fragment
import com.ms.daelimtime.fragment.Sch_SurveyFragment
import com.ms.daelimtime.fragment.Stu_SurveyFragment
import com.ms.daelimtime.fragment.UserInfo_Fragment
import com.ms.daelimtime.util.DBHelper


class MainActivity : AppCompatActivity() {
    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

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

    } override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            var tempTime = System.currentTimeMillis();
            var intervalTime = tempTime - backPressedTime;
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return
            }
        }
        super.onBackPressed();
    }
}
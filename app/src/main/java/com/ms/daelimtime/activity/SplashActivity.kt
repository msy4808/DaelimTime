package com.ms.daelimtime.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.ms.daelimtime.R
import com.kakao.sdk.common.util.Utility

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var keyHash = Utility.getKeyHash(this) //카카오에서 지원하는 메소드로 해시키 구하기
        Log.d("키해시", keyHash)
        Handler().postDelayed({ startActivity(Intent(this, LoginActivity::class.java)) }, 1000L) //1초 딜레이 후 화면전환
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
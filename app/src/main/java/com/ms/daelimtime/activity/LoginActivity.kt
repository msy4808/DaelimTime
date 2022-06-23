package com.ms.daelimtime.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.ms.daelimtime.R

import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient
import com.ms.daelimtime.util.DBHelper

class LoginActivity : AppCompatActivity() {
    val spinner: Spinner by lazy {
        findViewById(R.id.class_spinner)
    }
    val userClassNum_edit : EditText by lazy{
        findViewById(R.id.userClassNum_edit)
    }
    lateinit var classNum : String
    lateinit var userClass : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //학과 학번 받기
        UserInit()

        var nextBtn : Button = findViewById(R.id.next_Btn)
        nextBtn.setOnClickListener{

            //

            userClass = spinner.getSelectedItem().toString();
            //학번
            this.classNum = userClassNum_edit.text.toString()
            //
            Log.e("dd","학번 ${classNum},  학과 ${userClass}")
            var intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

            //DB
            DBHelper.sendUserData(userClass,classNum)
        }



        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            } else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                getUserData()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

//        val keyHash = Utility.getKeyHash(this)
//        Log.d("Hash", keyHash)


        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                //사용자 정보(이메일 등) 가져오는 코드
                getUserData()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }


        val kakao_Btn = findViewById<ImageButton>(R.id.kakao_login_button) // 로그인 버튼

        kakao_Btn.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)


            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }


        }
    }

    private fun getUserData() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("kakaoTest", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    "kakaoTest", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
                DBHelper.id = user.id
                Log.d("id값 1", "${DBHelper.id}")
                DBHelper.email = user.kakaoAccount?.email
                DBHelper.name = user.kakaoAccount?.profile?.nickname
                DBHelper.profile_Src = user.kakaoAccount?.profile?.thumbnailImageUrl
            }
        }
    }

    private fun UserInit() {

        //학과선택 스피너
        ArrayAdapter.createFromResource(
            this,
            R.array.class_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


//        DBHelper.userClassNum = this.classNum
//        DBHelper.userClass = this.userClass


    }
}
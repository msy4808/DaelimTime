package com.ms.daelimtime.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.ms.daelimtime.R
import com.ms.daelimtime.util.DBHelper
import com.ms.daelimtime.util.DBHelper.userClass
import com.ms.daelimtime.util.DBHelper.userClassNum
import com.ms.daelimtime.util.DBHelper.userNickName


class UserInfo_Fragment : Fragment() {

    lateinit var mySurvey_btn : Button
    lateinit var Update : Button


    lateinit var userName_edit : EditText
    lateinit var userNickname_edit : EditText
    lateinit var userClassNum_edit : EditText
    lateinit var userClass_edit : EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_info, container, false)
        init(view)



        return view
    }

    fun init(view : View){
        //EditText 선언부
        userName_edit = view.findViewById(R.id.userName_edit) //카카오로 받은 사용자 이름
        userNickname_edit = view.findViewById(R.id.user_nickName_edit) // 닉네임
        userClassNum_edit = view.findViewById(R.id.user_classNum_edit) // 학번
        userClass_edit = view.findViewById(R.id.user_class_edit) //학과

        //My Info setText
        userName_edit.setText(DBHelper.name)

        userClassNum_edit.setText(DBHelper.userClassNum)
        userClass_edit.setText(DBHelper.userClass)
        userNickname_edit.setText(DBHelper.userNickName)


        mySurvey_btn = view.findViewById(R.id.myServey_btn)
        mySurvey_btn.setOnClickListener{
            getParentFragmentManager().beginTransaction().replace(R.id.home_ly,mySurvey_Fragment()).commit()

        }

    }


}




package com.ms.daelimtime.fragment

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.ms.daelimtime.R


class UserInfo_Fragment : Fragment() {

    lateinit var my_Survey : Button
    lateinit var Update : Button

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
        getUserData(view)
        return view
    }

    fun init(view : View){
        my_Survey = view.findViewById(R.id.my_Survey)
        my_Survey.setOnClickListener{
            getParentFragmentManager().beginTransaction().replace(R.id.home_ly,mySurvey_Fragment()).commit()
        }

    }

    fun getUserData(view : View){
        userClassNum_edit = view.findViewById(R.id.user_class_edit)
        userClass_edit = view.findViewById(R.id.user_classNum_edit)
    }
}




package com.ms.daelimtime.adapter

import android.util.Log

class SurveyModel(var title: String, var doc: String) {

    val TAG: String = "로그"

    //기본 생성자
    init{
        Log.d(TAG, "SurveyModel - init() called")
    }
}
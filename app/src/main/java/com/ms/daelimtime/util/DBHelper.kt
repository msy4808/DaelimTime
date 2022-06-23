package com.ms.daelimtime.util

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ms.daelimtime.activity.email

private lateinit var database: DatabaseReference //데이터베이스 레퍼런서 선언

class DBHelper {
    init{
        database = Firebase.database.reference //레퍼런스 초기화
    }

    public fun getUser(){
        database.child("User").child("DT_${email}").setValue("TEST")
    }
}
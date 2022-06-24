package com.ms.daelimtime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.ms.daelimtime.R
import com.ms.daelimtime.util.DBHelper

class Result_A_Activity : AppCompatActivity() {
    private lateinit var title: String
    private lateinit var doc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_a)
        title = intent.getStringExtra("title").toString()
        doc = intent.getStringExtra("doc").toString()

        val resultTitle = findViewById<TextView>(R.id.result_Title)
        val resultDoc = findViewById<TextView>(R.id.result_Doc)
        val agreeBar = findViewById<ProgressBar>(R.id.agreeBar)
        val opposeBar = findViewById<ProgressBar>(R.id.opposeBar)
        var agree = 0
        var oppose = 0

        resultTitle.text = title
        resultDoc.text = doc

        DBHelper.database.child("Result").child("SC_${title}").get().addOnSuccessListener {
            when(it.value) {
                null -> {
                    DBHelper.database.child("Result").child("ST_${title}").get().addOnSuccessListener{
                        agree = Integer.parseInt(it.child("agree").getValue().toString())
                        oppose = Integer.parseInt(it.child("oppose").getValue().toString())
                        agreeBar.max = agree + oppose
                        agreeBar.progress = agree
                        opposeBar.progress = oppose
                    }
                }
                else -> {
                    agree = Integer.parseInt(it.child("agree").getValue().toString())
                    oppose = Integer.parseInt(it.child("oppose").getValue().toString())
                    agreeBar.max = agree + oppose
                    agreeBar.progress = agree
                    opposeBar.progress = oppose
                }
            }
        }
    }
}
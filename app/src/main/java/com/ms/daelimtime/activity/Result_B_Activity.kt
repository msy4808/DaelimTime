package com.ms.daelimtime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.ms.daelimtime.R
import com.ms.daelimtime.util.DBHelper

class Result_B_Activity : AppCompatActivity() {
    private lateinit var title: String
    private lateinit var doc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_b)

        title = intent.getStringExtra("title").toString()
        doc = intent.getStringExtra("doc").toString()

        val resultTitle = findViewById<TextView>(R.id.result_Title)
        val resultDoc = findViewById<TextView>(R.id.result_Doc)
        val vgBar = findViewById<ProgressBar>(R.id.vgBar)
        val gBar = findViewById<ProgressBar>(R.id.gBar)
        val mBar = findViewById<ProgressBar>(R.id.mBar)
        val bBar = findViewById<ProgressBar>(R.id.bBar)
        val vbBar = findViewById<ProgressBar>(R.id.vbBar)
        var veryGood:Int = 0
        var good:Int = 0
        var middle:Int = 0
        var bad:Int = 0
        var veryBad:Int = 0

        resultTitle.text = title
        resultDoc.text = doc

        DBHelper.database.child("Result").child("SC_${title}").get().addOnSuccessListener {
            when(it.value) {
                null -> {
                    DBHelper.database.child("Result").child("ST_${title}").get().addOnSuccessListener{
                        veryGood = Integer.parseInt(it.child("verygood").getValue().toString())
                        good = Integer.parseInt(it.child("good").getValue().toString())
                        middle = Integer.parseInt(it.child("middle").getValue().toString())
                        bad = Integer.parseInt(it.child("bad").getValue().toString())
                        veryBad = Integer.parseInt(it.child("verybad").getValue().toString())

                        vgBar.progress = veryGood
                        gBar.progress = good
                        mBar.progress = middle
                        bBar.progress = bad
                        vbBar.progress = veryBad

                    }
                }
                else -> {
                    veryGood = Integer.parseInt(it.child("verygood").getValue().toString())
                    good = Integer.parseInt(it.child("good").getValue().toString())
                    middle = Integer.parseInt(it.child("middle").getValue().toString())
                    bad = Integer.parseInt(it.child("bad").getValue().toString())
                    veryBad = Integer.parseInt(it.child("verybad").getValue().toString())
                    vgBar.max = veryGood + good + middle + bad + veryBad
                    gBar.max = veryGood + good + middle + bad + veryBad
                    mBar.max = veryGood + good + middle + bad + veryBad
                    bBar.max = veryGood + good + middle + bad + veryBad
                    vbBar.max = veryGood + good + middle + bad + veryBad

                    vgBar.progress = veryGood
                    gBar.progress = good
                    mBar.progress = middle
                    bBar.progress = bad
                    vbBar.progress = veryBad
                }
            }
        }
    }
}
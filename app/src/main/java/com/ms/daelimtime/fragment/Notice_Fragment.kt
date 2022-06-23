package com.ms.daelimtime.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.ms.daelimtime.R

class Notice_Fragment : Fragment() {
    lateinit var webView: WebView
    val url: String = "https://www.daelim.ac.kr/cms/FrCon/index.do?MENU_ID=900#page1"
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notice, container, false)

        webView = view.findViewById(R.id.webView)

        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        webView.loadUrl(url)




        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack())
                {
                    webView.goBack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    }





//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d("msg", "코루틴 실행")
//
//        }

//   private fun getData() {
//
//   }
//
//
//    suspend fun getNotice(){
//        var isEnd = false
//        val url: String = "https://www.daelim.ac.kr/cms/FrCon/index.do?MENU_ID=50#page1"
//        try{
//            val jsoup = Jsoup.connect(url)
//            val doc = jsoup.maxBodySize(0).get()
//            val text = Jsoup.connect(url).get().text()
//
//            val info = doc.select("#tbody .txtL").select("a").text().toString()
//            Log.d("info",info)
//
//            if (info != null){
//                isEnd = true
//            }
//        }
//        catch (httpStatusException : HttpStatusException){
//            isEnd = true
//            Log.e("error", httpStatusException.message.toString())
//            httpStatusException.printStackTrace()
//        }
//        isEnd
//    }








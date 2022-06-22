package com.ms.daelimtime.activity

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication:Application() {
    override fun onCreate(){
        super.onCreate()
        KakaoSdk.init(this,"cf49f3a69a895bcd942074fdfb471d52")
    }

}
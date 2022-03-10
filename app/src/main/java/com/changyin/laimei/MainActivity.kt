package com.changyin.laimei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.changyin.laimei.databinding.ActivityMainBinding
import com.ystar.ystarbaselib.baseact.BaseAct
import com.ystar.ystarbaselib.view.BaseViewmodel

class MainActivity : BaseAct<ActivityMainBinding,BaseViewmodel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

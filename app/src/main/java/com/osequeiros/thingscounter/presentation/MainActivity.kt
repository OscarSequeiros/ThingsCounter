package com.osequeiros.thingscounter.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.osequeiros.thingscounter.R
import com.osequeiros.thingscounter.presentation.view.ItemsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("MainActivity", "onCreate")

        val fragment = ItemsFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.frameMain, fragment)
            commitAllowingStateLoss()
        }
    }
}

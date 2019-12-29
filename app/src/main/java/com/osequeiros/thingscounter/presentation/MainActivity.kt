package com.osequeiros.thingscounter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.osequeiros.thingscounter.R
import com.osequeiros.thingscounter.presentation.view.ItemsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ItemsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameMain, fragment)
            commit()
        }
    }
}

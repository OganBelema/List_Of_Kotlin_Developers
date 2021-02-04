package com.belema.listofkotlindevelopers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belema.listofkotlindevelopers.R
import com.belema.listofkotlindevelopers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}
package com.alix01z.noteappkotlin_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alix01z.noteappkotlin_mvvm.R
import com.alix01z.noteappkotlin_mvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainA:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainA = DataBindingUtil.setContentView(this , R.layout.activity_main)

    }
}
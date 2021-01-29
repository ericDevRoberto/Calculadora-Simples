package com.project.estudo.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.project.estudo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(navView, navController)

        NavigationUI.setupActionBarWithNavController(this,navController, container)
    }

    //add lateral Navigation Menu
//    override fun onSupportNavigateUp(): Boolean{
//        val navController = this.findNavController(R.id.navHostFragment)
//        return NavigationUI.navigateUp(navController, container)
//    }
}
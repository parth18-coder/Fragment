package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        /*
        We need to find the NavController. Since we’re in the Activity now, we’ll use the alternate method of finding
        the controller from the ID of our NavHostFragment using the KTX extension function.
        */


        //Link the NavController to our ActionBar.
        //val navController = this.findNavController(R.id.myNavHostFragment)
        // Navigation controller has a UI library called NavigationUI.So it connect the actionbar with the
        // navController on its own.
        //NavigationUI.setupActionBarWithNavController(this, navController)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)

        /*
        Finally, we need to have the Activity handle the navigateUp action from our Activity. To do this we
        override onSupportNavigateUp, find the nav controller, and then we call navigateUp().
        */

        /*
        activities have onSupportNavigateUp method that we can override
         */


    }

    // we can use ctrl+o to get the methods that we can override then after searching on clicking enter we get
    // prototype of the function

    /*
    In this method we have to tell what should happen when up button is clicked
     */

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp()
    }
}
package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fragment.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        drawerLayout=binding.drawerlayout

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

        NavigationUI.setupActionBarWithNavController(this, navController,drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        //NavigationUI.setupWithNavController(binding.navView,navController)
        findViewById<NavigationView>(R.id.navView).setupWithNavController(navController)
        //appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        /*
        Finally, we need to have the Activity handle the navigateUp action from our Activity. To do this we
        override onSupportNavigateUp, find the nav controller, and then we call navigateUp().
        */

        /*
        activities have onSupportNavigateUp method that we can override
         */



        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // when navigation drawer is open than toggle button get converted into back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    // we can use ctrl+o to get the methods that we can override then after searching on clicking enter we get
    // prototype of the function

    /*
    In this method we have to tell what should happen when up button is clicked
     */

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        //return NavigationUI.navigateUp(navController,drawerLayout)
        return navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
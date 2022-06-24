package com.example.barmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.barmenu.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var bottomNavView: BottomNavigationView? = null
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)
        initUI()

    }

    private fun initBottomNav(){
        bottomNavView = binding?.bottomNavView
        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.alcoholFragment,
            R.id.nonAlcoholFragment,
        ))
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        bottomNavView?.setupWithNavController(navController!!)

    }

    private fun initNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.findNavController().run {
            binding?.toolbar?.setupWithNavController(this, AppBarConfiguration(setOf(R.id.alcoholFragment, R.id.nonAlcoholFragment)))
        }
    }

    private fun initUI(){
        initBottomNav()
        initNavigation()
    }

}
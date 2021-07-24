package com.shree.personalutilities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shree.personalutilities.views.goals.GoalsFragment
import com.shree.personalutilities.views.home.HomeFragment
import com.shree.personalutilities.views.todos.TodoFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        findViewById<BottomNavigationView>(R.id.bottomNavigationDashboard).apply {
            setOnNavigationItemReselectedListener(this@MainActivity)
            setOnNavigationItemSelectedListener(this@MainActivity)
            selectedItemId = R.id.homeMenuDashboard
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerDashboard, getHomeFragment()).commit()

        findViewById<Toolbar>(R.id.toolbarMainActivity).also {
            setSupportActionBar(it)
        }


    }

    override fun onNavigationItemReselected(item: MenuItem) {
        Log.d(null, "Menu reselected")
        
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.homeMenuDashboard -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerDashboard, getHomeFragment()).commit()
            true
        }
        R.id.goalsMenuDashboard -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerDashboard, getGoalsFragment()).commit()
            true
        }
        R.id.todosMenuDashboard -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerDashboard, getToDoFragment()).commit()
            true
        }
        else -> false
    }

    private fun getHomeFragment(): HomeFragment = HomeFragment.newInstance()
    private fun getGoalsFragment(): GoalsFragment = GoalsFragment.newInstance()
    private fun getToDoFragment(): TodoFragment = TodoFragment.newInstance()
}
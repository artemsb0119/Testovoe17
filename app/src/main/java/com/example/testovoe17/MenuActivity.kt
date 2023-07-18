package com.example.testovoe17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testovoe17.databinding.ActivityMenuBinding
import com.google.android.material.tabs.TabLayout

class MenuActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textViewScrolling = findViewById<TextView>(R.id.textViewScrolling)
        textViewScrolling.isSelected = true

        tabLayout = findViewById(R.id.tabLayout)

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "Home"
        tab1.setIcon(R.drawable.home)
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tab2.text = "Coins"
        tab2.setIcon(R.drawable.stats)
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "Watchlist"
        tab3.setIcon(R.drawable.star)
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tab4.text = "Coins"
        tab4.setIcon(R.drawable.coins)
        tabLayout.addTab(tab4)

        val tab5: TabLayout.Tab = tabLayout.newTab()
        tab5.text = "Alerts"
        tab5.setIcon(R.drawable.bell)
        tabLayout.addTab(tab5)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                val fragmentTransaction = supportFragmentManager.beginTransaction()

                when (position) {
                    1 -> {
                        val favoriteFragment = Coins()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, favoriteFragment)
                            .commit()
                    }
                    0 -> {
                        val homeFragment = Home()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .commit()
                    }
                }

                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        replaceFragment(Home())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, Home(), "HomeFragment")
        fragmentTransaction.commit()
    }
}
package io.github.jitinsharma.reduxmovieexample.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.jitinsharma.reduxmovieexample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_list -> {
                return true
            }
            R.id.navigation_favorites -> {
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, MovieListFragment())
                .commit()
    }
}

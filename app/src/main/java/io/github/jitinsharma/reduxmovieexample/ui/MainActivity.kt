package io.github.jitinsharma.reduxmovieexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.roughike.bottombar.OnTabSelectListener
import io.github.jitinsharma.reduxmovieexample.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnTabSelectListener {

    override fun onTabSelected(tabId: Int) {
        when(tabId) {
            R.id.tab_list -> {

            }
            R.id.tab_favorite -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomBar.setOnTabSelectListener(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, MovieListFragment())
                .commit()
    }
}

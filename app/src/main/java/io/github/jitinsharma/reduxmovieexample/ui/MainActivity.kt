package io.github.jitinsharma.reduxmovieexample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.roughike.bottombar.BottomBarTab
import com.roughike.bottombar.OnTabSelectListener
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.checkForFavorites
import io.github.jitinsharma.reduxmovieexample.helpers.transact
import io.github.jitinsharma.reduxmovieexample.states.FavoriteCounterState
import io.github.jitinsharma.reduxmovieexample.store
import kotlinx.android.synthetic.main.activity_main.*
import tw.geothings.rekotlin.StoreSubscriber

@SuppressLint("PrivateResource")
class MainActivity : AppCompatActivity(), OnTabSelectListener, StoreSubscriber<FavoriteCounterState?> {
    private lateinit var nearby: BottomBarTab

    override fun newState(state: FavoriteCounterState?) {
        state?.apply {
            if (favoriteCount != 0) {
                nearby.setBadgeCount(favoriteCount)
            }
        }
    }

    override fun onTabSelected(tabId: Int) {
        when (tabId) {
            R.id.tab_list -> {
                showFragment(MovieListFragment())
            }
            R.id.tab_favorite -> {
                showFragment(MovieListFragment())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomBar.setOnTabSelectListener(this)
        nearby = bottomBar.getTabWithId(R.id.tab_favorite)
        showFragment(MovieListFragment())
        store.dispatch(checkForFavorites())
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.favoriteCounterState
            }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    private fun showFragment(fragment: Fragment) {
        transact {
            replace(R.id.container, fragment)
            setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }
}
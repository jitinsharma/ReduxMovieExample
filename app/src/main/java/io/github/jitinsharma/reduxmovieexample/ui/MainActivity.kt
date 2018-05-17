package io.github.jitinsharma.reduxmovieexample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import com.roughike.bottombar.BottomBarTab
import com.roughike.bottombar.OnTabSelectListener
import dagger.android.support.DaggerAppCompatActivity
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.CheckForFavorites
import io.github.jitinsharma.reduxmovieexample.helpers.transact
import io.github.jitinsharma.reduxmovieexample.states.AppStore
import io.github.jitinsharma.reduxmovieexample.states.FavoriteCounterState
import kotlinx.android.synthetic.main.activity_main.*
import tw.geothings.rekotlin.StoreSubscriber
import javax.inject.Inject

@SuppressLint("PrivateResource")
class MainActivity : DaggerAppCompatActivity(), OnTabSelectListener, StoreSubscriber<FavoriteCounterState?> {

    @Inject
    lateinit var store: AppStore

    private lateinit var favoriteTab: BottomBarTab

    override fun newState(state: FavoriteCounterState?) {
        state?.apply {
            if (favoriteCount != 0) {
                favoriteTab.setBadgeCount(favoriteCount)
            }
        }
    }

    override fun onTabSelected(tabId: Int) {
        when (tabId) {
            R.id.tab_list -> {
                showFragment(MovieListFragment())
            }
            R.id.tab_favorite -> {
                showFragment(FavoriteListFragment())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomBar.setOnTabSelectListener(this)
        favoriteTab = bottomBar.getTabWithId(R.id.tab_favorite)
        showFragment(MovieListFragment())
        store.dispatch(CheckForFavorites())
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
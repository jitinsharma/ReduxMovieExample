package io.github.jitinsharma.reduxmovieexample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.LoadFavoriteMovies
import io.github.jitinsharma.reduxmovieexample.helpers.makeGone
import io.github.jitinsharma.reduxmovieexample.helpers.makeVisible
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.AppStore
import io.github.jitinsharma.reduxmovieexample.states.FavoriteListState
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import tw.geothings.rekotlin.StoreSubscriber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteListFragment : DaggerFragment(), StoreSubscriber<FavoriteListState?> {

    @Inject
    lateinit var store: AppStore

    private lateinit var movieListAdapter: MovieListAdapter

    override fun newState(state: FavoriteListState?) {
        state?.apply {
            if (displayNoFavoriteMessage) {
                noFavoriteText.makeVisible()
                return
            }
            favorites?.let {
                noFavoriteText.makeGone()
                favoriteList.makeVisible()
                initializeAdapter(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.dispatch(LoadFavoriteMovies())
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.favoriteListState
            }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        movieListAdapter = MovieListAdapter(store, movieObjects, true)
        favoriteList.layoutManager = GridLayoutManager(context, 2)
        favoriteList.adapter = movieListAdapter
    }
}
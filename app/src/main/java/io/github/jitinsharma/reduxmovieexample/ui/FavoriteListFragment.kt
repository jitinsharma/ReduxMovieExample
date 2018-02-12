package io.github.jitinsharma.reduxmovieexample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.loadFavoriteMovies
import io.github.jitinsharma.reduxmovieexample.helpers.makeGone
import io.github.jitinsharma.reduxmovieexample.helpers.makeVisible
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.FavoriteListState
import io.github.jitinsharma.reduxmovieexample.store
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import tw.geothings.rekotlin.StoreSubscriber

/**
 * A simple [Fragment] subclass.
 */
class FavoriteListFragment : Fragment(), StoreSubscriber<FavoriteListState?> {
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
        store.dispatch(loadFavoriteMovies())
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
        movieListAdapter = MovieListAdapter(movieObjects, true) { _, _ -> }
        favoriteList.layoutManager = GridLayoutManager(context, 2)
        favoriteList.adapter = movieListAdapter
    }
}
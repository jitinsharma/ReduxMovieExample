package io.github.jitinsharma.reduxmovieexample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.loadTopRatedMovies
import io.github.jitinsharma.reduxmovieexample.favoritesClicked
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.MovieListState
import io.github.jitinsharma.reduxmovieexample.storage.MovieDBHelper
import io.github.jitinsharma.reduxmovieexample.store
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.jetbrains.anko.toast
import tw.geothings.rekotlin.StoreSubscriber

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment(), StoreSubscriber<MovieListState?> {
    lateinit var movieDBHelper : MovieDBHelper

    override fun newState(state: MovieListState?) {
        state?.movieObjects?.let {
            initializeAdapter(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDBHelper = MovieDBHelper()
        store.dispatch(loadTopRatedMovies())
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        val movieListAdapter = MovieListAdapter(movieObjects) { clickType, movieObject ->
            when(clickType) {
                favoritesClicked -> {
                    movieDBHelper.insertMovieAsync(movieObject) {
                        context?.toast("${movieObject.title} added to favorites")
                    }
                }
            }
        }
        movieList.layoutManager = GridLayoutManager(context, 2)
        movieList.adapter = movieListAdapter
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.movieListState
            }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }
}

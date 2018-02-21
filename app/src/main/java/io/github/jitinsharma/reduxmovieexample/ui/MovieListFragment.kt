package io.github.jitinsharma.reduxmovieexample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.LoadTopRatedMovies
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.MovieListState
import io.github.jitinsharma.reduxmovieexample.store
import kotlinx.android.synthetic.main.fragment_movie_list.*
import tw.geothings.rekotlin.StoreSubscriber

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment(), StoreSubscriber<MovieListState?> {
    private lateinit var movieListAdapter: MovieListAdapter

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
        store.dispatch(LoadTopRatedMovies())
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        movieListAdapter = MovieListAdapter(movieObjects)
        movieList.layoutManager = GridLayoutManager(context, 2)
        movieList.adapter = movieListAdapter
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.movieListState
            }.skipRepeats()
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }
}

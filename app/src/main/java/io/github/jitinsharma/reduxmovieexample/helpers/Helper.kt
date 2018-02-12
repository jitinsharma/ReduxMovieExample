package io.github.jitinsharma.reduxmovieexample.helpers

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.RequiresPermission
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import io.github.jitinsharma.reduxmovieexample.BuildConfig
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by jsharma on 15/01/18.
 */

const val favoritesClicked = "favoritesClicked"
const val imageClicked = "imageClicked"
const val imageUrlPrefix = "http://image.tmdb.org/t/p/w185/"

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .into(this)
}

inline fun debugMode(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

fun <T> asyncCoroutinesExecutor(heavyFunction: () -> T, response: (response: T?) -> Unit) {
    async(UI) {
        val data: Deferred<T> = bg { heavyFunction() }
        response(data.await())
    }
}

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = this
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}

inline fun Context.withConnection(block: () -> Unit) {
    if (isNetworkStatusAvailable()) {
        block()
    }
}

inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.makeVisible() { this.visibility = View.VISIBLE }

fun View.makeGone() { this.visibility = View.GONE }

fun View.makeInvisible() { this.visibility = View.INVISIBLE }

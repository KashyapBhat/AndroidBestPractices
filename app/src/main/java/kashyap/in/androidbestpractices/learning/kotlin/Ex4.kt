package kashyap.`in`.androidbestpractices.learning.kotlin

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle

class ExampleAsIs : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        asAndis(this)
    }

    private fun asAndis(context: Context) {
        if (context is Activity) {
//            do something
        }
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}
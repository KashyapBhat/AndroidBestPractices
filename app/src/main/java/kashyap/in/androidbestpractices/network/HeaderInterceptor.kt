package kashyap.`in`.androidbestpractices.network

import android.content.Context
import android.util.Log
import kashyap.`in`.androidbestpractices.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val response = proceed(
            request()
                .newBuilder()
                .addHeader("appId", "")
                .addHeader("device", "android")
                .addHeader("Authorization", BuildConfig.AUTH_KEY)
                .build()
        )
        response
    }
}

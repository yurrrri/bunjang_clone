package leeyuri.mobile.bunjang_mok_aos.config

import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import leeyuri.mobile.bunjang_mok_aos.config.ApplicationClass.Companion.sf
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sf.getString(X_ACCESS_TOKEN, null)
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}
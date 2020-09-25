package kz.bukenov.weather.data.network.interceptor

import net.oauth.OAuth
import net.oauth.OAuthAccessor
import net.oauth.OAuthConsumer
import net.oauth.OAuthMessage
import okhttp3.Interceptor
import okhttp3.Response


class OAuth1Interceptor : Interceptor {
    companion object {
        const val CONSUMER_KEY =
            "dj0yJmk9NVdjU3BETkpPam5LJmQ9WVdrOVZVNHdUbTFJTjJRbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTY2"
        const val CONSUMER_SECRET = "8d9ada990bdcc8b61ca1607033c00470b175bc53"
        const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val consumer = OAuthConsumer(null, CONSUMER_KEY, CONSUMER_SECRET, null)
        consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1)
        val accessor = OAuthAccessor(consumer)
        val request = accessor.newRequestMessage(OAuthMessage.GET, origin.url().toString(), null)
        val authorization = request.getAuthorizationHeader(null)
        val builder = origin.newBuilder()
            .header(AUTHORIZATION, authorization)
        return chain.proceed(builder.build())
    }
}
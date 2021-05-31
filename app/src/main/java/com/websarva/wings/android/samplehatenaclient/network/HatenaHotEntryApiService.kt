package com.websarva.wings.android.samplehatenaclient.network

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.TypeConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import com.websarva.wings.android.samplehatenaclient.model.Rss
import retrofit2.Retrofit
import retrofit2.http.GET

private val baseUrl = "https://b.hatena.ne.jp"

val tikXml: TikXml = TikXml.Builder()
    .addTypeConverter(String::class.java, HtmlEscapeStringConverter())
    .exceptionOnUnreadXml(false)
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(TikXmlConverterFactory.create(tikXml))
    .build()

interface HatenaHotEntryApiService {
    @GET("hotentry.rss")
    suspend fun getHotEntries(): Rss
}

private class HtmlEscapeStringConverter : TypeConverter<String> {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun read(value: String?): String {
        return Html.fromHtml(value ?: "", Html.FROM_HTML_MODE_COMPACT).toString()
    }

    override fun write(value: String?): String {
        throw Error("not supported")
    }
}

object HatenaHotEntryApi {
    val hatenaHotEntryService : HatenaHotEntryApiService by lazy {
        retrofit.create(HatenaHotEntryApiService::class.java)
    }
}
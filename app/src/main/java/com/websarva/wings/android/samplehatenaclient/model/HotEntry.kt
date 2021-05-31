package com.websarva.wings.android.samplehatenaclient.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "item")
data class HotEntry(
    @PropertyElement(name = "title")
    val title: String,

    @PropertyElement(name = "link")
    val link: String,

    @PropertyElement(name = "description")
    val description: String,

    @PropertyElement(name = "hatena:bookmarkcount")
    val bookmarkCount: Int,

    @PropertyElement(name = "hatena:imageurl")
    val imageUrl: String?
)
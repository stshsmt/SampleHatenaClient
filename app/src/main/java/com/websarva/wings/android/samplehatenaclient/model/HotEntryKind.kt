package com.websarva.wings.android.samplehatenaclient.model

enum class HotEntryKind(i: Int) {
    ALL(0) {
        override fun getDisplayName() = "総合"
    },
    SOCIAL(1) {
        override fun getDisplayName() = "世の中"
    },
    ECONOMICS(2) {
        override fun getDisplayName() = "政治と経済"
    },
    LIFE(3) {
        override fun getDisplayName() = "暮らし"
    };

    abstract fun getDisplayName(): String

    companion object {
        fun findFromValue(value: Int): HotEntryKind? {
            return when(value) {
                0 -> ALL
                1 -> SOCIAL
                2 -> ECONOMICS
                3 -> LIFE
                else -> null
            }
        }
    }
}
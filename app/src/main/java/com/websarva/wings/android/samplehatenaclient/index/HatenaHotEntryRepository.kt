package com.websarva.wings.android.samplehatenaclient.index

import com.websarva.wings.android.samplehatenaclient.model.HotEntryKind
import com.websarva.wings.android.samplehatenaclient.model.Rss
import com.websarva.wings.android.samplehatenaclient.network.HatenaHotEntryApi
import com.websarva.wings.android.samplehatenaclient.network.HatenaHotEntryApiService

class HatenaHotEntryRepository(private val apiService: HatenaHotEntryApiService) {
    companion object {
        fun create(): HatenaHotEntryRepository {
            return HatenaHotEntryRepository(HatenaHotEntryApi.hatenaHotEntryService)
        }
    }
    suspend fun getHotEntries(hotEntryKind: HotEntryKind): Rss {
        val hotEntries = when (hotEntryKind) {
            HotEntryKind.ALL -> apiService.getHotEntries()
            HotEntryKind.ECONOMICS -> apiService.getEconomicsHotEntries()
            HotEntryKind.LIFE -> apiService.getLifeHotEntries()
            HotEntryKind.SOCIAL -> apiService.getSocialHotEntries()
        }

        return hotEntries
    }
}
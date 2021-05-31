package com.websarva.wings.android.samplehatenaclient.index

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.samplehatenaclient.model.HotEntry
import com.websarva.wings.android.samplehatenaclient.network.HatenaHotEntryApi
import kotlinx.coroutines.launch
import java.lang.Exception

class IndexViewModel : ViewModel() {
    private val _hotEntries = MutableLiveData<List<HotEntry>>()
    val hotEntries: LiveData<List<HotEntry>>
        get() = _hotEntries

    init {
        getHotEntries()
    }

    private fun getHotEntries() {
        val hatenaHotEntryService = HatenaHotEntryApi.hatenaHotEntryService
        viewModelScope.launch {
            try {
                val rss = hatenaHotEntryService.getHotEntries()
                _hotEntries.value = rss.items
            } catch (e: Exception) {
                //TODO: エラー時の処理
                Log.e("getHotEntries", e.message.toString())
            }
        }
    }
}
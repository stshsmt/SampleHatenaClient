package com.websarva.wings.android.samplehatenaclient.index

import android.util.Log
import androidx.lifecycle.*
import com.websarva.wings.android.samplehatenaclient.model.HotEntry
import com.websarva.wings.android.samplehatenaclient.model.HotEntryKind
import kotlinx.coroutines.launch
import java.lang.Exception

class IndexViewModel(private val repository: HatenaHotEntryRepository) : ViewModel() {
    private val _hotEntries = MutableLiveData<List<HotEntry>>()
    val hotEntries: LiveData<List<HotEntry>>
        get() = _hotEntries

    private val _navigateToHotEntry = MutableLiveData<HotEntry>()
    val navigateToHotEntry: LiveData<HotEntry>
        get() = _navigateToHotEntry

    fun getHotEntries(hotEntryKind: HotEntryKind) {
        viewModelScope.launch {
            try {
                val rss = repository.getHotEntries(hotEntryKind)
                _hotEntries.value = rss.items
            } catch (e: Exception) {
                //TODO: エラー時の処理
                Log.e("getHotEntries", e.message.toString())
            }
        }
    }

    fun onHotEntryClicked(hotEntry: HotEntry) {
        _navigateToHotEntry.value = hotEntry
    }

    fun onHotEntryNavigated() {
        _navigateToHotEntry.value = null
    }
}

class IndexViewModelFactory(private val repository: HatenaHotEntryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndexViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IndexViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
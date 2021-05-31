package com.websarva.wings.android.samplehatenaclient.index

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.samplehatenaclient.databinding.FragmentIndexBinding

class FragmentIndex : Fragment() {
    private val viewModel: IndexViewModel by lazy {
        val factory = IndexViewModelFactory(HatenaHotEntryRepository.create())
        ViewModelProvider(this, factory).get(IndexViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getHotEntries()

        val binding = FragmentIndexBinding.inflate(inflater)

        val adapter = HotEntriesAdapter()
        binding.hotEntries.adapter = adapter
        binding.hotEntries.layoutManager = LinearLayoutManager(activity)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.hotEntries.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
package com.websarva.wings.android.samplehatenaclient.index

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.samplehatenaclient.databinding.FragmentIndexBinding
import com.websarva.wings.android.samplehatenaclient.layout_base.IndexBaseFragmentDirections
import com.websarva.wings.android.samplehatenaclient.model.HotEntryKind

// FIXME: 宣言場所はここではまずい気がする
val hotEntryKindKey = "hotEntryKindKey"

class FragmentIndex : Fragment() {
    private val viewModel: IndexViewModel by lazy {
        val factory = IndexViewModelFactory(HatenaHotEntryRepository.create())
        ViewModelProvider(this, factory).get(IndexViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hotEntryKind = requireArguments().get(hotEntryKindKey) as HotEntryKind
        viewModel.getHotEntries(hotEntryKind)

        val binding = FragmentIndexBinding.inflate(inflater)

        val adapter = HotEntriesAdapter(HotEntryItemClickListener { hotEntry ->
            viewModel.onHotEntryClicked(hotEntry)
        })
        binding.hotEntries.adapter = adapter
        binding.hotEntries.layoutManager = LinearLayoutManager(activity)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.hotEntries.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToHotEntry.observe(viewLifecycleOwner, Observer { hotEntry ->
            hotEntry?.let {
                this.findNavController().navigate(
                        IndexBaseFragmentDirections
                                .actionToHotEntry(hotEntry.link)
                )

                viewModel.onHotEntryNavigated()
            }
        })

        return binding.root
    }
}
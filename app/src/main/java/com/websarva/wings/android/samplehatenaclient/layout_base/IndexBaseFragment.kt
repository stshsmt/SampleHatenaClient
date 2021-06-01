package com.websarva.wings.android.samplehatenaclient.layout_base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.websarva.wings.android.samplehatenaclient.databinding.FragmentIndexBaseBinding
import com.websarva.wings.android.samplehatenaclient.index.FragmentIndex
import com.websarva.wings.android.samplehatenaclient.index.hotEntryKindKey
import com.websarva.wings.android.samplehatenaclient.model.HotEntryKind

class IndexBaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentIndexBaseBinding.inflate(inflater, container, false)
        val pagerAdapter = PagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            val hotEntryKind = HotEntryKind.findFromValue(position)
            tab.text = hotEntryKind?.getDisplayName()
        }.attach()

        return binding.root
    }
}

private class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return HotEntryKind.values().size
    }

    override fun createFragment(position: Int): Fragment {
        val hotEntryKind = HotEntryKind.findFromValue(position)
        val fragment = FragmentIndex()
        fragment.arguments = bundleOf(hotEntryKindKey to hotEntryKind)

        return fragment
    }
}
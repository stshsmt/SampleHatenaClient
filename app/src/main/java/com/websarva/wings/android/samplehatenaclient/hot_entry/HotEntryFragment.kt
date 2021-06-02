package com.websarva.wings.android.samplehatenaclient.hot_entry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.websarva.wings.android.samplehatenaclient.R
import com.websarva.wings.android.samplehatenaclient.databinding.FragmentHotEntryBinding

class HotEntryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let { HotEntryFragmentArgs.fromBundle(it) }

        val binding = FragmentHotEntryBinding.inflate(inflater)
        binding.webview.settings.javaScriptEnabled = true
        if (args != null) {
            binding.webview.loadUrl(args.url)
        }

        return binding.root
    }
}
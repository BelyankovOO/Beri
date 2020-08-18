package beri.android.supportModules.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import beri.android.supportModules.Fragment.ImageFragment

class OnboardingAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(position+1)
    }

    override fun getCount(): Int {
        return 3
    }

}
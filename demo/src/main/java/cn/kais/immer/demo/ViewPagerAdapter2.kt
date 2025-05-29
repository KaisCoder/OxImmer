package cn.kais.immer.demo;

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  1:57 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class ViewPagerAdapter2(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<Fragment> by lazy { SparseArray<Fragment>() }

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var fragment = fragments[position]
        if (fragment != null) return fragment
        fragment = when (position) {
            0 -> ImageTextFragment.newInstance(R.drawable.yurisa__005)
            1 -> ImageTextFragment.newInstance(R.drawable.yurisa__002)
            2 -> ImageTextFragment.newInstance(R.drawable.yurisa__003)
            3 -> ImageTextFragment.newInstance(R.drawable.yurisa__004)
            else -> Fragment()
        }
        fragments.put(position, fragment)
        return fragment
    }

}
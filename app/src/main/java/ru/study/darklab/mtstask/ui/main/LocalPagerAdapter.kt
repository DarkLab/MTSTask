package ru.study.darklab.mtstask.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class LocalPagerAdapter(private val context: Context, private val dataObjectList: List<View>) :
    PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

    override fun getCount(): Int {
        return dataObjectList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = dataObjectList[position]
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "TAB ${position + 1}"
    }
}
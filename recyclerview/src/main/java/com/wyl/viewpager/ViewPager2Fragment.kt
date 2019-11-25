package com.wyl.viewpager

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.wyl.recyclerview.R
import kotlinx.android.synthetic.main.fragment_view_pager2.*
import kotlinx.android.synthetic.main.item_viewpager.view.*


/**
 * PagerTabStrip
 */
class ViewPager2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager2, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewpager.setPageMarginDrawable()
        listOf(
            Item("我是红色", Color.RED),
            Item("我是绿色", Color.GREEN),
            Item("我是蓝色", Color.BLUE)
        ).let {
            viewpager.apply {
                adapter = ViewPagerPageAdapter(requireContext(), it)
                pageMargin = 32
            }
        }

        pager_strip.apply {
            tabIndicatorColor = Color.BLUE
        }

    }

    private data class Item(val name: String, @ColorInt val color: Int)

    private class ViewPagerPageAdapter(
        context: Context,
        private val items: List<Item>
    ) : PagerAdapter() {

        private val views by lazy {
            items.map { obtainView(context, it) }
        }

        private fun obtainView(context: Context, item: Item): View {
            return View.inflate(context, R.layout.item_viewpager, null).apply {
                text.text = item.name
                text.setBackgroundColor(item.color)
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return items[position].name
        }

        /**
         * 判断从instantiateItem（）返回来的Key与当前的View是否能对应起来
         */
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return (view === `object`).apply {
                println("ViewPagerPageAdapter.isViewFromObject: $this")
            }
        }

        /**
         * 返回要滑动的VIew的个数
         */
        override fun getCount(): Int {
            return 3.apply {
                println("ViewPagerPageAdapter.getCount")
            }
        }

        /**
         * 做了两件事，第一：将当前视图添加到container中，第二：返回当前View
         */
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            println("ViewPagerPageAdapter.instantiateItem")
            return views[position].apply {
                container.addView(this)
            }
        }

        /**
         * 从当前container中删除指定位置（position）的View;
         */
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            println("ViewPagerPageAdapter.destroyItem")
            container.removeView(`object` as View)
        }


    }
}
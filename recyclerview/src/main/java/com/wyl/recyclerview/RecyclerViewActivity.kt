package com.wyl.recyclerview

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.ditclear.bindingadapter.SingleTypeAdapter
import kotlinx.android.synthetic.main.recyclerview_activity.*

/**
 * 分割线的学习
 * 2019年7月11日 15:48:15
 */
class RecyclerViewActivity : AppCompatActivity() {

    private val dataSource = ObservableArrayList<String>()

    private val itemDecoration by lazy {
        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
//                super.getItemOffsets(outRect, view, parent, state)
                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.top = 1
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_activity)

        initData()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SingleTypeAdapter(context, R.layout.recyclerview_item_main, dataSource)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

    }

    private fun initData() {
        dataSource.addAll(List(100) {
            "item$it"
        })
    }

}

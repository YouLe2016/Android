package com.wyl.android.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyl.android.R
import com.wyl.android.databinding.Room2ItemBinding
import io.ditclear.bindingadapterx.BindingViewHolder
import io.ditclear.bindingadapterx.ItemDecorator
import io.ditclear.bindingadapterx.SingleTypeAdapter
import kotlinx.android.synthetic.main.room1_activity.btAdd
import kotlinx.android.synthetic.main.room1_activity.btDelete
import kotlinx.android.synthetic.main.room1_activity.btUpdate
import kotlinx.android.synthetic.main.room2_activity.*

class Room2Activity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(Room2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room2_activity)

        viewModel.getAllWords().observe(this, Observer {
            viewModel.dataSource.clear()
            viewModel.dataSource.addAll(it)
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Room2Activity)
            adapter = mAdapter
        }

        btAdd.setOnClickListener {
            viewModel.addWords(
                Word("hello", "哈喽"),
                Word("word", "世界"),
                Word("English", "英语"),
                Word("Chinese", "中文"),
                Word("apple", "苹果"),
                Word("banana", "香蕉"),
                Word("pear", "梨"),
                Word("watermelon", "西瓜"),
                Word("peach", "桃")
            )
        }

        btDelete.setOnClickListener {
            viewModel.deleteWords(
                Word("Hello", "哈喽").apply { wid = 5 },
                Word("Hello", "哈喽").apply { wid = 10 }
            )
        }

        btUpdate.setOnClickListener {
            viewModel.updateWords(
                Word("MyLove", "我的爱人").apply { wid = 3 },
                Word("MyLove", "我的爱人").apply { wid = 6 },
                Word("MyLove", "我的爱人").apply { wid = 9 }
            )
        }

    }

    private val mAdapter by lazy {
        SingleTypeAdapter(this, R.layout.room2_item, viewModel.dataSource).apply {
            itemDecorator = object : ItemDecorator {
                override fun decorator(
                    holder: BindingViewHolder<ViewDataBinding>,
                    position: Int,
                    viewType: Int
                ) {
                    (holder.binding as Room2ItemBinding).position = (position + 1).toString()
                }
            }
        }
    }

}

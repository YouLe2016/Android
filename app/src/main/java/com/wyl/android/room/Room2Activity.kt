package com.wyl.android.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R
import kotlinx.android.synthetic.main.room1_activity.*

class Room2Activity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(Room2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room1_activity)

        viewModel.getAllWords().observe(this, Observer {
            textView.text = it.joinToString("\n")
        })

        btAdd.setOnClickListener {
            viewModel.addWords(
                Word("Hello", "哈喽"),
                Word("Word", "世界")
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

}

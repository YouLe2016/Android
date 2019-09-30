package com.wyl.android.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RxRoom
import com.wyl.android.R
import io.reactivex.Observer
import kotlinx.android.synthetic.main.room1_activity.*

class Room1Activity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            this,
            WordDataBase::class.java,
            "word_database"
        ).allowMainThreadQueries().build()
    }

    private val wordDao by lazy { db.wordDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room1_activity)

        updateUi()

        btAdd.setOnClickListener {
            wordDao.addWords(
                Word("Hello", "哈喽"),
                Word("Word", "世界")
            )
            updateUi()
        }

        btDelete.setOnClickListener {
            wordDao.deleteWords(Word("Hello", "哈喽"))
            updateUi()
        }

    }

    private fun updateUi() {
        val string = wordDao.findAllWords().joinToString("\n")
        textView.text = string
    }


}

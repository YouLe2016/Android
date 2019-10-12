package com.wyl.word

import android.content.Context
import com.wyl.word.WordDataBase
import kotlin.concurrent.thread

class RoomRepository(context: Context) {

    private val db by lazy { WordDataBase.getInstance(context) }

    private val wordDao by lazy { db.wordDao() }

    val wordsList by lazy { wordDao.findAllWords() }

    fun addWords(vararg word: Word) {
        thread { wordDao.addWords(*word) }
    }

    fun deleteWords(vararg word: Word) {
        thread { wordDao.deleteWords(*word) }
    }

    fun updateWords(vararg word: Word) {
        thread { wordDao.updateWords(*word) }
    }

}
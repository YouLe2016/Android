package com.wyl.android.room

import android.content.Context
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
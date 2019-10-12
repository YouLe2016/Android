package com.wyl.word

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.wyl.word.WordDataBase
import kotlin.concurrent.thread

class RoomRepository(context: Context) {

    private val db by lazy { WordDataBase.getInstance(context) }

    private val wordDao by lazy { db.wordDao() }

    fun addWords(vararg word: Word) {
        thread { wordDao.addWords(*word) }
    }

    fun deleteWords(vararg word: Word) {
        thread { wordDao.deleteWords(*word) }
    }

    fun updateWords(vararg word: Word) {
        thread { wordDao.updateWords(*word) }
    }

    fun findAllWords(pattern: String? = null): LiveData<List<Word>> {
        return if (pattern.isNullOrEmpty()) {
            wordDao.findAllWords()
        } else {
            wordDao.findAllWordsByWord(pattern)
        }
    }

}
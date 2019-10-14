package com.wyl.word

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlin.concurrent.thread

class WordRepository(context: Context) {

    private val db by lazy { WordDataBase.getInstance(context) }

    private val wordDao by lazy { db.wordDao() }

    private lateinit var wordLiveData: LiveData<List<Word>>

    fun addWords(vararg word: Word) {
        thread { wordDao.addWords(*word) }
    }

    fun deleteWords(vararg word: Word) {
        thread { wordDao.deleteWords(*word) }
    }

    fun deleteAllWords() {
        thread { wordDao.deleteAllWords() }
    }

    fun updateWords(vararg word: Word) {
        thread { wordDao.updateWords(*word) }
    }

    fun findAllWords(pattern: LiveData<String>): LiveData<List<Word>> {
        wordLiveData = Transformations.switchMap(pattern) {
            if (it.isNullOrEmpty()) {
                wordDao.findAllWords()
            } else {
                wordDao.findAllWordsByWord("%$it%")
            }
        }
        return wordLiveData
    }

}
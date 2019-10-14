package com.wyl.word

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val wordRepository by lazy {
        WordRepository(application.applicationContext)
    }

    private val patternLiveData = MutableLiveData<String>()

    fun findAllWords(pattern: String? = null): LiveData<List<Word>> {
        patternLiveData.value = pattern
        return wordRepository.findAllWords(patternLiveData)
    }

    fun addWords(vararg word: Word) = wordRepository.addWords(*word)

    fun deleteWords(vararg word: Word) = wordRepository.deleteWords(*word)

    fun deleteAllWords() = wordRepository.deleteAllWords()

    fun updateWords(vararg word: Word) = wordRepository.updateWords(*word)


}
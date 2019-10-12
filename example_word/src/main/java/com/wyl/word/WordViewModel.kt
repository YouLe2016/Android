package com.wyl.word

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.regex.Pattern

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepository by lazy {
        RoomRepository(application.applicationContext)
    }

    fun findAllWords(pattern: String? = null): LiveData<List<Word>> =
        roomRepository.findAllWords(pattern)

    fun addWords(vararg word: Word) = roomRepository.addWords(*word)

    fun deleteWords(vararg word: Word) = roomRepository.deleteWords(*word)

    fun updateWords(vararg word: Word) = roomRepository.updateWords(*word)

}
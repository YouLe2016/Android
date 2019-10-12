package com.wyl.word

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepository by lazy {
        RoomRepository(application.applicationContext)
    }

    val dataSource = ObservableArrayList<Word>()

    fun getAllWords() = roomRepository.wordsList

    fun addWords(vararg word: Word) = roomRepository.addWords(*word)

    fun deleteWords(vararg word: Word) = roomRepository.deleteWords(*word)

    fun updateWords(vararg word: Word) = roomRepository.updateWords(*word)

}
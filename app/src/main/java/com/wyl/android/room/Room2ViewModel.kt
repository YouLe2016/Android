package com.wyl.android.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class Room2ViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepository by lazy {
        RoomRepository(application.applicationContext)
    }

    fun getAllWords() = roomRepository.wordsList

    fun addWords(vararg word: Word) = roomRepository.addWords(*word)

    fun deleteWords(vararg word: Word) = roomRepository.deleteWords(*word)

    fun updateWords(vararg word: Word) = roomRepository.updateWords(*word)

}
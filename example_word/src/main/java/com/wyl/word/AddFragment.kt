package com.wyl.word

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.room.util.StringUtil
import com.wyl.word.extend.hideKeyboard
import com.wyl.word.extend.showKeyboard
import kotlinx.android.synthetic.main.fragment_add.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(WordViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().showKeyboard()

        etEnglish.addTextChangedListener(watcher)
        etChinese.addTextChangedListener(watcher)

        button.isEnabled = false
        button.setOnClickListener {
            viewModel.addWords(
                Word(etEnglish.text.trim().toString(), etChinese.text.trim().toString())
            )
            findNavController().navigateUp()
        }

    }

    override fun onDestroy() {
        requireActivity().hideKeyboard()
        super.onDestroy()
    }

    private val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            button.isEnabled = etEnglish.text.isNotBlank() && etChinese.text.isNotBlank()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
}
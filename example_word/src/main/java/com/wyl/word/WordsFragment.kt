package com.wyl.word


import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wyl.word.extend.toast
import kotlinx.android.synthetic.main.fragment_words.*
import kotlinx.android.synthetic.main.item_words.view.*

/**
 * A simple [Fragment] subclass.
 */
class WordsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 让Fragment中的Menu显示出来 默认为false不显示
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.findAllWords().observe(this@WordsFragment, Observer {
            mAdapter.dataSource = it
            mAdapter.notifyDataSetChanged()
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_wordsFragment_to_addFragment)
        }
    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(WordViewModel::class.java) }

    private val mAdapter by lazy { WordsAdapter() }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.words_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            maxWidth = 700
            setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String): Boolean {
                        viewModel.findAllWords(newText)
                        return false
                    }

                    override fun onQueryTextSubmit(query: String): Boolean = false
                }
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clearData) {
            AlertDialog.Builder(requireContext())
                .setMessage("确认删除")
                .setPositiveButton("确认") { _, _ ->
                    viewModel.deleteAllWords()
                }.setNegativeButton("取消") { dialog, _ ->
                    dialog.cancel()
                }.show()
        }
        return super.onOptionsItemSelected(item)
    }

}

class WordsAdapter : RecyclerView.Adapter<ViewHolder>() {
    var dataSource = listOf<Word>()
//        假数据
//        var  dataSource = MutableList(100) {
//                Word("word$it", "chineseMeaning$it", it % 2 == 0)
//            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_words, parent, false
        )

        val viewHolder = ViewHolder(view)

        view.setOnClickListener {
            it.context.toast("${viewHolder.layoutPosition} ${viewHolder.adapterPosition}")
        }
        view.switch1.setOnCheckedChangeListener { _, isChecked ->
            dataSource[viewHolder.adapterPosition].hideMeaning = isChecked
            view.tvChinese.visibility = if (isChecked) View.GONE else View.VISIBLE
        }

        return viewHolder
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            tvNo.text = (position + 1).toString()
            tvEnglish.text = dataSource[position].word
            tvChinese.text = dataSource[position].chineseMeaning
            switch1.isChecked = dataSource[position].hideMeaning
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)



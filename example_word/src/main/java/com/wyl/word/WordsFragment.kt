package com.wyl.word


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
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
            //            mAdapter.dataSource = it
//            mAdapter.notifyDataSetChanged()
            // 方式一
//            mAdapter.notifyItemInserted(0)
            // 方式二
            mAdapter.submitList(it)
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
            itemAnimator = object : DefaultItemAnimator() {
                override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                    if (viewHolder.adapterPosition != 0) return

                    Log.d("tag", "tag-----------${viewHolder.adapterPosition}--")
                    layoutManager?.let {
                        val manager = it as LinearLayoutManager
                        val range =
                            manager.findFirstVisibleItemPosition()..manager.findLastVisibleItemPosition()
                        for (pos in range) {
                            val holder = findViewHolderForLayoutPosition(pos) as ViewHolder
                            holder.itemView.tvNo.text = (pos + 1).toString()
                        }
                    }
                    scrollToPosition(0)
                }
            }
        }

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_wordsFragment_to_addFragment)
        }
    }

    /**
     * 平滑滚动所使用的类
     */
    private val scroller by lazy {
        object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(WordViewModel::class.java) }

    private val mAdapter = WordsAdapter1()

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

private val DIFF_CALLBACK by lazy {
    object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.wid == newItem.wid
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }
}

class WordsAdapter1 : ListAdapter<Word, ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_words, parent, false
        )

        val viewHolder = ViewHolder(view)

        view.setOnClickListener {
            it.context.toast(getItem(viewHolder.adapterPosition).word)
        }
        view.switch1.setOnCheckedChangeListener { _, isChecked ->
            getItem(viewHolder.adapterPosition).hideMeaning = isChecked
            view.tvChinese.visibility = if (isChecked) View.GONE else View.VISIBLE
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            getItem(position).let {
                tvNo.text = (position + 1).toString()
                tvEnglish.text = it.word
                tvChinese.text = it.chineseMeaning
                switch1.isChecked = it.hideMeaning
            }
        }
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



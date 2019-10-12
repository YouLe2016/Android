package com.wyl.word


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
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
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAllWords().observe(this, Observer {
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

    private val mAdapter by lazy { Adapter() }

    class Adapter : RecyclerView.Adapter<ViewHolder>() {
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
}



package com.wyl.android.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wyl.android.BR
import com.wyl.android.R
import com.wyl.android.databinding.ItemPagingBinding
import io.ditclear.bindingadapterx.BindingViewHolder
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(ConcertViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        viewModel.dataList.observe(this, Observer {
            mAdapter.submitList(it)
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@PagingActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private val mAdapter by lazy { RecyclerViewAdapter() }

}

private val DIFFER_CALLBACK by lazy {
    object : DiffUtil.ItemCallback<Concert>() {
        override fun areItemsTheSame(oldItem: Concert, newItem: Concert): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Concert, newItem: Concert): Boolean =
            oldItem == newItem
    }
}

class RecyclerViewAdapter :
    PagedListAdapter<Concert, BindingViewHolder<ItemPagingBinding>>(DIFFER_CALLBACK) {
    override fun onBindViewHolder(holder: BindingViewHolder<ItemPagingBinding>, position: Int) {
        holder.binding.data = getItem(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ItemPagingBinding> {
        val binding = ItemPagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

}
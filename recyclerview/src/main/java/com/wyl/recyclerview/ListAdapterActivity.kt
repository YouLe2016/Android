package com.wyl.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.wyl.recyclerview.databinding.ItemListAdapterBinding
import io.ditclear.bindingadapter.BindingViewHolder
import io.ditclear.bindingadapter.SingleTypeAdapter
import kotlinx.android.synthetic.main.activity_list_adapter.*
import kotlinx.android.synthetic.main.activity_list_adapter.recyclerView
import kotlinx.android.synthetic.main.recyclerview_activity.*

class ListAdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_adapter)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        UserAdapter.submitList(List(10) {
            User(it, "Name$it", 90f)
        })

    }
}

val DiffUtil_ItemCallback = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}

val UserAdapter = object :
    ListAdapter<User, BindingViewHolder<ItemListAdapterBinding>>(DiffUtil_ItemCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ItemListAdapterBinding> {
        val binding =
            ItemListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder<ItemListAdapterBinding>,
        position: Int
    ) {
        holder.binding.user = getItem(position)
    }

}

data class User(var id: Int, var name: String, var score: Float)
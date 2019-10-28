package com.wyl.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_list_adapter.*

/**
 * ListAdapter的用法
 */
class ListAdapterActivity : AppCompatActivity() {
    private var data = List(10) {
        Data(imgUrl, "name $it", "desc $it")
    }

    private val mAdapter by lazy {
        DataAdapter().apply {
            submitList(data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_adapter)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
        }

        btRefresh.setOnClickListener {
            val list = List(10) {
                Data(imgUrl2, "name $it", "desc $it")
            }
            data = list
            mAdapter.submitList(data)
        }

        btLoadMore.setOnClickListener {
            val list = List(10) {
                val i = it + data.size
                Data(imgUrl2, "name $i", "desc $i")
            }
            data = data + list
            mAdapter.submitList(data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.notify_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.allRefresh -> {
                data = List(10) {
                    Data(imgUrl2, "refresh $it", "refresh $it")
                }
                mAdapter.submitList(data)
                true
            }
            R.id.removeItem -> {
                data = ArrayList(data).apply { removeAt(1) }
                mAdapter.submitList(data)
                true
            }
            R.id.insertItem -> {
                data = ArrayList(data).apply { add(1, Data(imgUrl2, "insert", "insert")) }
                mAdapter.submitList(data)
                true
            }
            R.id.moveItem -> {
                ArrayList(data).apply {
                    val temp = this[1]
                    this[1] = this[3]
                    this[3] = temp
                    data = this
                    mAdapter.submitList(data)
                }
                true
            }
            R.id.refreshItem -> {

                ArrayList(data).apply {
                    this[9] = Data(imgUrl2, "name 9", "desc 9")
                    data = this
                    mAdapter.submitList(data)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class DataAdapter : ListAdapter<Data, ViewHolder>(object : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notify, parent, false)

        val holder = ViewHolder(view)

        view.setOnClickListener {
            Log.d(TAG, "adapterPosition = ${holder.adapterPosition}")
            Log.d(TAG, "layoutPosition = ${holder.layoutPosition}")
            Log.d(TAG, "data = ${getItem(holder.adapterPosition)}")
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvName.text = getItem(position).name
            tvDesc.text = getItem(position).desc
            Glide.with(ivAvatar).load(getItem(position).url).into(ivAvatar)
        }
    }

}
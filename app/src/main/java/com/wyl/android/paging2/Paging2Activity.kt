package com.wyl.android.paging2

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wyl.android.R
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.android.synthetic.main.item_paging2.view.*

class Paging2Activity : AppCompatActivity() {
    private val Tag by lazy { localClassName }

    private val mAdapter by lazy { PagingAdapter() }

    private val mLayoutManager by lazy { LinearLayoutManager(this) }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(context, mLayoutManager.orientation)
            )
        }
        viewModel.dataSource.observe(this, Observer {
            mAdapter.submitList(it)
            it.addWeakCallback(null, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {
                    Log.d(Tag, "onChanged: position=$position, count=$count")
                    Log.d(Tag, it.toString())
                }

                override fun onInserted(position: Int, count: Int) {
                    Log.d(Tag, "onInserted: position=$position, count=$count")
                }

                override fun onRemoved(position: Int, count: Int) {
                    Log.d(Tag, "onRemoved: position=$position, count=$count")
                }
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_page2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_generate -> {
                viewModel.insertData()
            }
            R.id.action_clear -> {
                viewModel.deleteAll()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

class PagingAdapter : PagedListAdapter<Student, StudentViewHolder>(
    object : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paging2, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

}

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.textView5

    fun bindData(item: Student?) {
        if (item != null) {
            textView.text = item.number.toString()
        } else {
            textView.text = Resources.getSystem().getString(R.string.loading)
        }
    }
}



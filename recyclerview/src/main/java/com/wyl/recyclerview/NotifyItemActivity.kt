package com.wyl.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_notify_item.*
import kotlinx.android.synthetic.main.item_notify.view.*
import kotlin.math.abs
import kotlin.math.min


const val imgUrl =
    "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=590921703,2385870203&fm=26&gp=0.jpg"
const val imgUrl2 =
    "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1571714253&di=6f8c93bc86d76c2641f81b0e944c390e&src=http://b-ssl.duitang.com/uploads/item/201704/28/20170428204318_NwHXe.jpeg"

/**
 * 1. 刷新全部的item，notifyDataSetChanged()
 * 2. 刷新指定的item，notifyItemChanged(int)
 * 3. 从指定的位置开始刷新指定个item，notifyItemRangeChanged(int,int) 这个刷新onBindViewHolder方法，position才能保持一直
 * 4. 插入、移动指定位置的item，并刷新，notifyItemInserted(int)、notifyItemMoved(int)、notifyItemRemoved(int) （有坑记得要填）
 * 5. 局部刷新指定的数据，notifyItemChanged(int, Object)
作者：不识水的鱼
链接：https://www.jianshu.com/p/a49e407474bb
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

const val TAG = "Look"

class NotifyItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_item)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
        }
    }

    private val mAdapter by lazy {
        NotifyItemAdapter().apply {
            dataSource.addAll(List(10) {
                Data(imgUrl, "name $it", "desc $it")
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.notify_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.allRefresh -> {
                val list = List(10) {
                    Data(imgUrl2, "refresh $it", "refresh $it")
                }
                mAdapter.addDataList(list)
                true
            }
            R.id.removeItem -> {
                mAdapter.removeItem(position = 1)
                true
            }
            R.id.insertItem -> {
                mAdapter.insertItem(1, Data(imgUrl2, "insert", "insert"))
                true
            }
            R.id.moveItem -> {
                mAdapter.moveItem(1, 3)
                true
            }
            R.id.refreshItem -> {
                mAdapter.refreshItem(9, Data(imgUrl2, "refresh", "refresh"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

class NotifyItemAdapter : RecyclerView.Adapter<ViewHolder>() {
    var dataSource = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notify, parent, false)

        val holder = ViewHolder(view)

        view.setOnClickListener {
            Log.d(TAG, "adapterPosition = ${holder.adapterPosition}")
            Log.d(TAG, "layoutPosition = ${holder.layoutPosition}")
            Log.d(TAG, "data = ${dataSource[holder.adapterPosition]}")
        }

        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvName.text = dataSource[position].name
            tvDesc.text = dataSource[position].desc
            Glide.with(ivAvatar).load(dataSource[position].url).into(ivAvatar)
        }
    }

    override fun getItemCount(): Int = dataSource.size

    fun addDataList(data: List<Data>) {
        dataSource.addAll(data)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int? = null) {
        if (position == null) return
        dataSource.removeAt(1) // 注释掉: UI底部自动补充一个data[size-1]
        notifyItemRemoved(1)
    }

    fun insertItem(position: Int, data: Data) {
        dataSource.add(position, data) // 注释掉: Boom
        notifyItemInserted(position)
    }

    // 其实是交换, 可以用move实现
    fun moveItem(fromPosition: Int, toPosition: Int) {
        //注意位置的变换 1 和 4 交换
//            val remove4 = dataSource.removeAt(4)
//            val remove1 = dataSource.removeAt(1)
//            dataSource.add(1, remove4)
//            dataSource.add(4, remove1)

        val data = dataSource[fromPosition]
        dataSource[fromPosition] = dataSource[toPosition]
        dataSource[toPosition] = data
        Log.d(TAG, dataSource.toString())
        notifyItemChanged(fromPosition)
        notifyItemChanged(toPosition)

        // 花式刷新
        // 注意位置的变换 1 和 3 交换
//            val remove4 = dataSource.removeAt(3)
//            val remove1 = dataSource.removeAt(1)
//            dataSource.add(1, remove4)
//            dataSource.add(3, remove1)
//            notifyItemMoved(3, 1)
//            //受影响的item都刷新position
//            notifyItemRangeChanged(
//                min(3, 1),
//                abs(3 - 1) + 1
//            )//受影响的item都刷新position
    }

    fun refreshItem(position: Int, data: Data) {
        dataSource[position] = data
        notifyItemChanged(position)
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivAvatar: ImageView = itemView.ivAvatar
    val tvName: TextView = itemView.tvName
    val tvDesc: TextView = itemView.tvDesc
}

data class Data(val url: String, val name: String, val desc: String)
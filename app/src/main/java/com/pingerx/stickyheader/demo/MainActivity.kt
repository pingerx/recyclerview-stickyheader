package com.pingerx.stickyheader.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pingerx.stickyheader.StickyRecyclerHeadersAdapter
import com.pingerx.stickyheader.StickyRecyclerHeadersDecoration
import com.pingerx.stickyheader.listener.OnHeaderClickListener
import com.pingerx.stickyheader.listener.StickyRecyclerHeadersTouchListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DemoAdapter()
        recyclerView.adapter = DemoAdapter()
        val headersDecor = StickyRecyclerHeadersDecoration(adapter)
        recyclerView.addItemDecoration(headersDecor)

        val touchListener = StickyRecyclerHeadersTouchListener(recyclerView, headersDecor)
        touchListener.setOnHeaderClickListener(object : OnHeaderClickListener {
            override fun onHeaderClick(header: View?, position: Int, headerId: Long) {
                Toast.makeText(recyclerView.context, "点击了$position", Toast.LENGTH_SHORT).show()
            }

            override fun onChildClick(child: View?, position: Int, headerId: Long) {
                if (child?.id == R.id.btnClick) {
                    Toast.makeText(recyclerView.context, "点击了$child", Toast.LENGTH_SHORT).show()
                }
            }
        })
        recyclerView.addOnItemTouchListener(touchListener)
    }

    inner class DemoAdapter : RecyclerView.Adapter<DemoHolder>(), StickyRecyclerHeadersAdapter<HeaderHolder> {

        override fun getChildClickIds(): IntArray {
            return intArrayOf(R.id.btnClick)
        }

        override fun getHeaderId(position: Int): Long {
            return if (position > 30) {
                123412341324
            } else {
                23412341234
            }
        }

        override fun onCreateHeaderViewHolder(parent: ViewGroup): HeaderHolder {
            return HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_header, parent, false))
        }

        override fun onBindHeaderViewHolder(holder: HeaderHolder?, position: Int) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoHolder {
            return DemoHolder(
                LayoutInflater.from(parent.context).inflate(
                    android.R.layout.activity_list_item,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return DataProvider.getData().size
        }

        override fun onBindViewHolder(holder: DemoHolder, position: Int) {
            holder.itemView.findViewById<ImageView>(android.R.id.icon).setImageResource(R.mipmap.ic_launcher)
            holder.itemView.findViewById<TextView>(android.R.id.text1).text = DataProvider.getData()[position]
        }
    }

    inner class DemoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

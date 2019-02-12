package com.pingerx.stickyheader.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pingerx.stickyheader.StickyRecyclerHeadersAdapter
import com.pingerx.stickyheader.StickyRecyclerHeadersDecoration
import com.pingerx.stickyheader.StickyRecyclerHeadersTouchListener
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
        touchListener.setOnHeaderClickListener { header, position, headerId ->
            Toast.makeText(recyclerView.context, "点击了$position", Toast.LENGTH_SHORT).show()


            header.findViewById<Button>(R.id.btnClick).setOnClickListener {
                Toast.makeText(recyclerView.context, "点击了", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.addOnItemTouchListener(touchListener)
    }

    inner class DemoAdapter : RecyclerView.Adapter<DemoHolder>(), StickyRecyclerHeadersAdapter<HeaderHolder> {
        override fun getHeaderId(position: Int): Long {
            return if (position % 10 == 0) {
                122312
            } else {
                12234234
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

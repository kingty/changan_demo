package com.changan.module_phonelist.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.changan.module_phonelist.data.Item

class ItemAdapter(context: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var datas: ArrayList<Item>? = ArrayList()
    val context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {


        val l = LinearLayout(context)
        val text1 = TextView(context);
        val text2 = TextView(context);
        l.addView(text1)
        l.addView(text2)

        return ItemViewHolder(l)
    }

    override fun getItemCount(): Int {
        return (if (datas == null) 0 else datas?.size)!!
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = datas?.get(position)

        val text1 = ((holder.itemView) as LinearLayout)[0] as TextView
        val text2 = ((holder.itemView) as LinearLayout)[1] as TextView
        if (item != null) {
            text1.text = "${item.id}         "
            text2.text = item.name
            text1.textSize = 20F
            text2.textSize = 20F
        }

    }

    fun setData(items: List<Item>) {
        datas?.clear()
        datas?.addAll(items)
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
package com.iflippie.level3_task2

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_link.view.*

class WebsitesAdapter(private val allLinks : List<Websites>, val clickListener: (Websites) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */

    class WebsitesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(linkText: Websites, clickListener: (Websites) -> Unit) {
            itemView.tvAdd.text = linkText.theLink.toString()
            itemView.setOnClickListener{clickListener(linkText)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.item_link,
            parent,
            false
        )
        return WebsitesViewHolder(view)
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return allLinks.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bind(allLinks[position], clickListener)
        (holder as WebsitesViewHolder).bind(allLinks[position], clickListener)
    }

}
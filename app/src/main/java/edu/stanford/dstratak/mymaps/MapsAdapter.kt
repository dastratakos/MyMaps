package edu.stanford.dstratak.mymaps

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.stanford.dstratak.mymaps.models.UserMap

private const val TAG = "MapsAdapter"
class MapsAdapter(val context: Context, val userMaps: List<UserMap>, val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
        fun onItemDelete(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_map, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userMaps.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userMap = userMaps[position]

        holder.itemView.findViewById<ImageButton>(R.id.btDelete).setOnClickListener {
            Log.i(TAG, "Deleted position $position")
            onClickListener.onItemDelete(position)
        }

        holder.itemView.setOnClickListener {
            Log.i(TAG, "Tapped on position $position")
            onClickListener.onItemClick(position)
        }

        val textViewTitle = holder.itemView.findViewById<TextView>(R.id.tvMapTitle)
        textViewTitle.text = userMap.title
        val textViewPlaceCount = holder.itemView.findViewById<TextView>(R.id.tvPlaceCount)
        val numPins = userMap.places.count()
        if (numPins == 1) {
            textViewPlaceCount.text = "$numPins pin"
        } else {
            textViewPlaceCount.text = "$numPins pins"
        }
        val textViewCreateDate = holder.itemView.findViewById<TextView>(R.id.tvCreateDate)
        textViewCreateDate.text = "Created: ${FORMAT.format(userMap.createTime)}"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

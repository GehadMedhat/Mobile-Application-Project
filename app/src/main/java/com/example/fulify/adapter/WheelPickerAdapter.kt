package com.example.fulify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fulify.R

/**
 * Adapter for wheel picker using RecyclerView
 */
class WheelPickerAdapter(
    private val values: List<Int>,
    private val unit: String = "",
    private var selectedPosition: Int = 0,
    private val onItemSelected: (Int, Int) -> Unit // position, value
) : RecyclerView.Adapter<WheelPickerAdapter.WheelViewHolder>() {

    inner class WheelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textValue: TextView = itemView.findViewById(R.id.tvWheelValue)
        val textUnit: TextView = itemView.findViewById(R.id.tvWheelUnit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WheelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wheel_picker, parent, false)
        return WheelViewHolder(view)
    }

    override fun onBindViewHolder(holder: WheelViewHolder, position: Int) {
        val value = values[position]
        holder.textValue.text = value.toString()

        // Show unit only on selected item
        if (position == selectedPosition) {
            holder.textUnit.text = unit
            holder.textUnit.visibility = View.VISIBLE
            // Selected item: green background, bold, black text
            holder.itemView.setBackgroundResource(R.drawable.bg_wheel_item_selected)
            holder.textValue.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.black)
            )
            holder.textValue.textSize = 24f
            holder.textValue.setTypeface(null, android.graphics.Typeface.BOLD)
        } else {
            holder.textUnit.visibility = View.GONE
            // Unselected item: transparent background, normal, gray text
            holder.itemView.setBackgroundResource(android.R.color.transparent)
            holder.textValue.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.gray)
            )
            holder.textValue.textSize = 20f
            holder.textValue.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        holder.itemView.setOnClickListener {
            val oldPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
            onItemSelected(selectedPosition, value)
        }
    }

    override fun getItemCount(): Int = values.size

    fun setSelectedPosition(position: Int) {
        val oldPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(oldPosition)
        notifyItemChanged(selectedPosition)
    }

    fun getSelectedValue(): Int = values[selectedPosition]
}
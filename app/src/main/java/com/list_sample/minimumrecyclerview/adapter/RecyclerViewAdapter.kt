package com.list_sample.minimumrecyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.list_sample.minimumrecyclerview.R
import com.list_sample.minimumrecyclerview.model.EvenNumberModel
import com.list_sample.minimumrecyclerview.model.OddNumberModel
import com.list_sample.minimumrecyclerview.model.SectionHeaderModel
import com.list_sample.minimumrecyclerview.model.section.SectionGroup

/**
 * Created by monkey on 2017/09/26.
 */
class RecyclerViewAdapter(context: Context, private val sectionGroup: SectionGroup): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    val EVEN_NUMBER = 0
    val ODD_NUMBER = 1
    val SECTION_HEADER = 2


    // ViewHolder
    inner class EvenNumberHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val evenNumberCellText = itemView.findViewById<TextView>(R.id.even_number_cell_text)
    }

    inner class OddNumberHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val oddNumberCellText = itemView.findViewById<TextView>(R.id.odd_number_cell_text)
    }

    inner class SectionHeaderHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.section_header_title_text)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.even_number_cell, parent, false)

        if (viewType == EVEN_NUMBER) {
            return EvenNumberHolder(inflater.inflate(R.layout.even_number_cell, parent, false))

        } else if (viewType == ODD_NUMBER) {
            return OddNumberHolder(inflater.inflate(R.layout.odd_number_cell, parent, false))
        } else if (viewType == SECTION_HEADER) {
            return SectionHeaderHolder(inflater.inflate(R.layout.section_header_cell, parent, false))
        }

        return EvenNumberHolder(inflater.inflate(R.layout.even_number_cell, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = sectionGroup.itemAt(position)

        if (getItemViewType(position) == EVEN_NUMBER) {
            holder as EvenNumberHolder
            item as EvenNumberModel

            holder.evenNumberCellText.text = item.cellText
        } else if (getItemViewType(position) == ODD_NUMBER) {
            holder as OddNumberHolder
            item as OddNumberModel

            holder.oddNumberCellText.text = item.cellText
        } else if (getItemViewType(position) == SECTION_HEADER) {
            holder as SectionHeaderHolder
            item as SectionHeaderModel

            holder.titleText.text = item.titleText
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = sectionGroup.itemAt(position)

        return when(item) {
            is EvenNumberModel -> {
                EVEN_NUMBER
            }
            is OddNumberModel -> {
                ODD_NUMBER
            }
            is SectionHeaderModel -> {
                SECTION_HEADER
            }
            else -> {
                throw AssertionError("no enum")
            }
        }
    }

    override fun getItemCount(): Int {
        return sectionGroup.totalCount()
    }
}
package com.myapp.traveldiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapp.traveldiary.DateHelper
import com.myapp.traveldiary.EventOverviewActivity
import com.myapp.traveldiary.R
import com.myapp.traveldiary.dal.dao.Diary
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ListDiariesAdapter : ListAdapter<Diary, ListDiariesAdapter.DiaryViewHolder>(DIFF_CALLBACK) {

    class DiaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.diary_name)
        private val location: TextView = view.findViewById(R.id.diary_location)
        private val startDate: TextView = view.findViewById(R.id.diary_start_date)
        private val endDate: TextView = view.findViewById(R.id.diary_end_date)
        //private val deleteButton: ImageView = view.findViewById(R.id.delete_button)

        fun bindTo(item: Diary) {
            name.apply {
                text = item.name

                onClick {
                    context.startActivity<EventOverviewActivity>(DIARY_ID to item.uid)
                }
            }

            location.apply {
                location.text = item.location
            }

            startDate.apply {
                startDate.text = DateHelper.parseToString(context, item.startDate)
            }

            endDate.apply {
                endDate.text = DateHelper.parseToString(context, item.endDate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiaryViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.entry_diary,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {

        private const val DIARY_ID = "com.myapp.traveldiary.DIARY_ID"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Diary>() {

            override fun areItemsTheSame(oldItem: Diary, newItem: Diary) =
                oldItem.uid == newItem.uid

            override fun areContentsTheSame(oldItem: Diary, newItem: Diary) =
                oldItem.toString() == oldItem.toString()
        }
    }
}

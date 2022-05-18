package com.help.ukraine.testkmm.android

import com.help.ukraine.testkmm.data.entities.RocketLaunchEntity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class LaunchesRvAdapter(var launches: List<RocketLaunchEntity>) :
    RecyclerView.Adapter<LaunchesRvAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_launch, parent, false)
            .run(::LaunchViewHolder)
    }

    override fun getItemCount(): Int = launches.size

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bindData(launches[position])
    }

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val missionNameTextView = itemView.findViewById<TextView>(R.id.missionName)
        private val launchYearTextView = itemView.findViewById<TextView>(R.id.launchYear)
        private val launchSuccessTextView = itemView.findViewById<TextView>(R.id.launchSuccess)
        private val missionDetailsTextView = itemView.findViewById<TextView>(R.id.details)

        fun bindData(launch: RocketLaunchEntity) {
            val ctx = itemView.context
            missionNameTextView.text =
                ctx.getString(R.string.mission_name_field, launch.missionName)
            launchYearTextView.text =
                ctx.getString(R.string.launch_year_field, launch.launchYear.toString())
            missionDetailsTextView.text =
                ctx.getString(R.string.details_field, launch.details ?: "")
            val launchSuccess = launch.launchSuccess ?: false
            if (launchSuccess) {
                launchSuccessTextView.text = ctx.getString(R.string.successful)
                launchSuccessTextView.setTextColor(
                    (ContextCompat.getColor(
                        itemView.context,
                        R.color.colorSuccessful
                    ))
                )
            } else {
                launchSuccessTextView.text = ctx.getString(R.string.unsuccessful)
                launchSuccessTextView.setTextColor(
                    (ContextCompat.getColor(
                        itemView.context,
                        R.color.colorUnsuccessful
                    ))
                )
            }
        }
    }
}
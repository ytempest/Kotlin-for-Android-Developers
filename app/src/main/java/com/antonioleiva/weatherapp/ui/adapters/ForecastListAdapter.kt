package com.antonioleiva.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antonioleiva.weatherapp.R
import com.antonioleiva.weatherapp.domain.model.Forecast
import com.antonioleiva.weatherapp.domain.model.ForecastList
import com.antonioleiva.weatherapp.extensions.ctx
import com.antonioleiva.weatherapp.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast.*

class ForecastListAdapter(private val weekForecast: ForecastList) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    private var itemClickListener: ((Forecast) -> Unit)? = null

    fun setItemClick(listener: ((Forecast) -> Unit)?): ForecastListAdapter {
        itemClickListener = listener
        return this;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size

    class ViewHolder(override val containerView: View, private val itemClick: ((Forecast) -> Unit)?)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(icon)
                dateText.text = date.toDateString()
                descriptionText.text = description
                maxTemperature.text = "${high}º"
                minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick?.invoke(this) }
            }
        }
    }

    // 这种写法效果与上面一样的
//    class ViewHolder(private val containerView: View, private val itemClick: ((Forecast) -> Unit)?)
//        : RecyclerView.ViewHolder(containerView) {
//
//        fun bindForecast(forecast: Forecast) {
//            with(forecast) {
//                Picasso.with(itemView.ctx).load(iconUrl).into(containerView.icon)
//                containerView.dateText.text = date.toDateString()
//                containerView.descriptionText.text = description
//                containerView.maxTemperature.text = "${high}º"
//                containerView.minTemperature.text = "${low}º"
//                itemView.setOnClickListener { itemClick?.invoke(this) }
//            }
//        }
//    }
}
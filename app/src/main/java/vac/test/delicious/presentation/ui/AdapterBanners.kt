package vac.test.delicious.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vac.test.delicious.R
import vac.test.delicious.databinding.BannerItemBinding

class AdapterBanners : ListAdapter<String, BannerViewHolder>(BannerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            BannerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val url = getItem(position)
        holder.bind(url)
    }

}

class BannerViewHolder(
    private val binding: BannerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(url: String) {
        Glide.with(binding.bannerView)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.bannerView)
    }
}

class BannerDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}


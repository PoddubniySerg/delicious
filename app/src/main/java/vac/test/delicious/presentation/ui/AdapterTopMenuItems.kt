package vac.test.delicious.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vac.test.delicious.databinding.TopMenuItemBinding
import vac.test.delicious.domain.entities.TopMenu
import vac.test.delicious.domain.model.TopMenuPresent

class AdapterTopMenuItems(
    private val onClick: (Int) -> Unit
) : ListAdapter<TopMenu, TopMenuItemViewHolder>(TopMenuItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMenuItemViewHolder {
        return TopMenuItemViewHolder(
            TopMenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopMenuItemViewHolder, position: Int) {
        val topMenu = getItem(position)
        holder.bind(topMenu) { onClick(position) }
    }
}

class TopMenuItemViewHolder(
    private val binding: TopMenuItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(topMenu: TopMenu, onClick: () -> Unit) {
        with(binding) {
            titleView.text = topMenu.title
            root.setOnClickListener { onClick() }
            if (topMenu is TopMenuPresent) {
                titleView.isSelected = topMenu.isSelected
            }
        }
    }
}

class TopMenuItemDiffUtil : DiffUtil.ItemCallback<TopMenu>() {

    override fun areItemsTheSame(oldItem: TopMenu, newItem: TopMenu): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TopMenu, newItem: TopMenu): Boolean {
        val titlesSame = oldItem.title == newItem.title
        return if (oldItem is TopMenuPresent && newItem is TopMenuPresent) {
            return titlesSame && oldItem.isSelected == newItem.isSelected
        } else {
            titlesSame
        }
    }
}


package vac.test.delicious.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vac.test.delicious.R
import vac.test.delicious.databinding.CardFoodItemBinding
import vac.test.delicious.domain.entities.CardFood

class AdapterCardsFood : ListAdapter<CardFood, CardFoodViewHolder>(CardsFoodDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardFoodViewHolder {
        return CardFoodViewHolder(
            CardFoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardFoodViewHolder, position: Int) {
        val cardFood = getItem(position)
        holder.bind(cardFood)
    }
}

class CardFoodViewHolder(
    private val binding: CardFoodItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cardFood: CardFood) {
        with(binding) {
            Glide.with(posterView)
                .load(cardFood.posterUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(posterView)
            titleView.text = cardFood.description
            descriptionView.text = cardFood.description
            priceFromButton.text = "от %sр".format(cardFood.priceFrom)
        }
    }
}

class CardsFoodDiffUtil : DiffUtil.ItemCallback<CardFood>() {

    override fun areItemsTheSame(oldItem: CardFood, newItem: CardFood): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardFood, newItem: CardFood): Boolean {
        return oldItem.title == newItem.title
                && oldItem.description == newItem.description
                && oldItem.posterUrl == newItem.posterUrl
                && oldItem.priceFrom == newItem.priceFrom
    }

}


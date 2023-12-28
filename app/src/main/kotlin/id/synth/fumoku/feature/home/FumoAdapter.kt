package id.synth.fumoku.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.synth.fumoku.R
import id.synth.fumoku.databinding.ItemFumoBinding
import id.synth.fumoku.model.Fumo

class FumoAdapter() : RecyclerView.Adapter<FumoAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFumoBinding) : RecyclerView.ViewHolder(binding.root)

    private val asyncListDiffer = AsyncListDiffer(this, Fumo.DiffCallback())
    var onClickListener: ((ViewHolder, Fumo) -> Unit)? = null
        get() = field
        set(value) {
            field = value
        }

    fun submit(newList: List<Fumo>) {
        asyncListDiffer.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        val binding = ItemFumoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fumo = asyncListDiffer.currentList[position]

        with(holder.binding) {
            id.text = fumo.idPadded
            character.text = fumo.character
            name.text = fumo.name

            type.text = fumo.type.toString()

            // Animation
            image.transitionName = "image"

            // Image
            Glide.with(root)
                .load(fumo.image)
                .into(image)
        }

        holder.itemView.setOnClickListener {
            onClickListener?.invoke(holder, fumo)
        }
    }

    override fun getItemCount() = asyncListDiffer.currentList.size
}
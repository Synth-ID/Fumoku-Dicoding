package id.synth.fumoku.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.synth.fumoku.databinding.ItemFumoBinding
import id.synth.fumoku.model.Fumo

class FumoAdapter() : RecyclerView.Adapter<FumoAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFumoBinding) : RecyclerView.ViewHolder(binding.root)

    private val asyncListDiffer = AsyncListDiffer(this, Fumo.DiffCallback())

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

        with(holder) {
            binding.id.text = fumo.idPadded
            binding.character.text = fumo.character
            binding.name.text = fumo.name

            binding.type.text = fumo.type.toString()

            Glide.with(binding.root)
                .load(fumo.image)
                .into(binding.image)
        }
    }

    override fun getItemCount() = asyncListDiffer.currentList.size
}
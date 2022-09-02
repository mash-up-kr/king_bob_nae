package com.example.king_bob_nae.features.mykkilog.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.databinding.ItemDetailStepBinding
import com.example.king_bob_nae.features.mykkilog.presentation.detail.domain.StepItem

class DetailStepAdapter(
    private val stepList: List<StepItem>
) : RecyclerView.Adapter<StepHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder {
        return StepHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_detail_step,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        holder.bind(stepList[position])
    }

    override fun getItemCount(): Int = stepList.count()
}

class StepHolder(private val binding: ItemDetailStepBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: StepItem) {
        binding.step = item
    }
}
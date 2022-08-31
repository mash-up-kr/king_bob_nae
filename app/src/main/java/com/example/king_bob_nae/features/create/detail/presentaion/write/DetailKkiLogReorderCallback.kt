package com.example.king_bob_nae.features.create.detail.presentaion.write

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DetailKkiLogReorderCallback(val adapter: RecipeItemTouchHelperAdapter) :
    ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        before: RecyclerView.ViewHolder,
        after: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(before.bindingAdapterPosition, after.bindingAdapterPosition)
        return true
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val clippedDx =
            clip(recyclerView.width, viewHolder.itemView.left, viewHolder.itemView.right, dX)
        val clippedDy =
            clip(recyclerView.height, viewHolder.itemView.top, viewHolder.itemView.bottom, dY)
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            clippedDx,
            clippedDy,
            actionState,
            isCurrentlyActive
        )
    }

    private fun clip(size: Int, start: Int, end: Int, delta: Float): Float {
        val newStart = start + delta
        val newEnd = end + delta

        val oldStart = 0 - newStart
        val oldEnd = newEnd - size

        return when {
            oldStart > 0 -> delta + oldStart
            oldEnd > 0 -> delta - oldEnd
            else -> delta
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}

interface RecipeItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder?)
}

interface RecipeItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
}

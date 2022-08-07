package com.example.king_bob_nae.features.imagepicker.presentation

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImagePickerGridDivider : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val position = parent.getChildAdapterPosition(view)
        val size1 = dpToPx(view.context, 1.5F)
        val size3 = dpToPx(view.context, 2.0F)

//        Log.d("tjrwn", "getItemOffsets: $size1  $size3")
        outRect.bottom = size3

        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        when (layoutParams.spanIndex) {
            0 -> {
                outRect.left = size1
                outRect.right = size1
            }
            1 -> {
                outRect.left = size1
                outRect.right = size1
            }
            2 -> {
                outRect.left = size1
                outRect.right = 0
            }
        }
    }

//    fun dpToPx(context: Context, dp: Float) = TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics
//    ).toInt()

    private fun dpToPx(context: Context, dp: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
    // 간격 대규형한테 물어보기
}

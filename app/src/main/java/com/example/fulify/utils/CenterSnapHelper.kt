package com.example.fulify.utils

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom SnapHelper that snaps to center and notifies position changes
 */
class CenterSnapHelper(
    private val onSnapPositionChange: (Int) -> Unit
) : LinearSnapHelper() {

    private var lastSnapPosition = RecyclerView.NO_POSITION

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        val view = super.findSnapView(layoutManager)

        if (view != null && layoutManager != null) {
            val snapPosition = layoutManager.getPosition(view)
            if (snapPosition != lastSnapPosition) {
                lastSnapPosition = snapPosition
                onSnapPositionChange(snapPosition)
            }
        }

        return view
    }
}
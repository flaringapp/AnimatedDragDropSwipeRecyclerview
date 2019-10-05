package com.ernestoyaquello.dragdropswiperecyclerview.animator

import android.view.View

/**
 * Interface for drag animations
 */
interface ItemDragAnimator {

    /**
     * Callback for invoking drag start animation
     * @param view The dragged item view
     */
    fun onDragStarted(view: View)

    /**
     * Callback for invoking drag end animation
     * @param view The dragged item view
     */
    fun onDragEnded(view: View)
}
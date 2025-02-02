package com.ernestoyaquello.dragdropswiperecyclerviewsample.config.local

enum class ListFragmentType(val index: Int, val tag: String) {
    VERTICAL(0, "VerticalFragment"),
    HORIZONTAL(1, "HorizontalFragment"),
    GRID(2, "GridFragment")
}

data class ListFragmentConfig(
        var isUsingStandardItemLayout: Boolean,
        var isRestrictingDraggingDirections: Boolean,
        var isUsingDragAnimations: Boolean,
        var isDragOnLongClick: Boolean,
        var isDrawingBehindSwipedItems: Boolean,
        var isUsingFadeOnSwipedItems: Boolean)

private val listFragmentConfigurations = listOf(
        ListFragmentConfig(
                isUsingStandardItemLayout = true,
                isRestrictingDraggingDirections = true,
                isUsingDragAnimations = false,
                isDragOnLongClick = false,
                isDrawingBehindSwipedItems = true,
                isUsingFadeOnSwipedItems = false
        ),
        ListFragmentConfig(
                isUsingStandardItemLayout = false,
                isRestrictingDraggingDirections = false,
                isUsingDragAnimations = false,
                isDragOnLongClick = false,
                isDrawingBehindSwipedItems = true,
                isUsingFadeOnSwipedItems = true
        ),
        ListFragmentConfig(
                false,
                isRestrictingDraggingDirections = false,
                isUsingDragAnimations = false,
                isDragOnLongClick = false,
                isDrawingBehindSwipedItems = true,
                isUsingFadeOnSwipedItems = true
        )
)

var currentListFragmentType = ListFragmentType.VERTICAL
val currentListFragmentConfig
    get() = listFragmentConfigurations[currentListFragmentType.index]
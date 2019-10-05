package com.ernestoyaquello.dragdropswiperecyclerview.animator

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator

/**
 * Default drag animator with scale and shake animations
 * @see ItemDragAnimator
 */
class DefaultDragAnimator : ItemDragAnimator {

    private var scaleAnimator: Animator? = null
    private var shakeAnimator: AnimatorSet? = null

    companion object {
        private const val SCALE_ANIM_DURATION = 250L
        private const val SHAKE_ANIM_DURATION = 175L

        private const val DRAG_SCALE = 1.1f
        private const val SHAKE_ROTATION_ANGLE_DELTA = 4f

        private const val SHAKE_PIVOT_X = 0.5f
        private const val SHAKE_PIVOT_Y = 0.75f
    }

    override fun onDragStarted(view: View) {
        scaleAnimator?.cancel()
        scaleAnimator = createStartDragScaleAnimator(view)
        scaleAnimator!!.start()

        shakeAnimator?.cancel()
        shakeAnimator = createStartShakeAnimator(view)
        shakeAnimator!!.start()
    }

    override fun onDragEnded(view: View) {
        scaleAnimator?.cancel()
        scaleAnimator = createEndDragScaleAnimator(view)
        scaleAnimator!!.start()

        shakeAnimator?.cancel()
        shakeAnimator = createEndShakeAnimator(view)
        shakeAnimator!!.start()
    }

    private fun createStartDragScaleAnimator(view: View): ValueAnimator {
        return createDragScaleAnimator(view, DRAG_SCALE)
    }

    private fun createEndDragScaleAnimator(view: View): ValueAnimator {
        return createDragScaleAnimator(view, 1f)
    }

    private fun createDragScaleAnimator(view: View, targetScale: Float): ValueAnimator {
        return ValueAnimator.ofFloat(view.scaleX, targetScale)
            .apply {
                interpolator = DecelerateInterpolator()
                duration = SCALE_ANIM_DURATION
                setTarget(view)
                addUpdateListener {
                    view.apply {
                        scaleX = animatedValue as Float
                        scaleY = animatedValue as Float
                    }
                }
            }
    }

    private fun createStartShakeAnimator(view: View): AnimatorSet {
        view.pivotX = view.measuredWidth * SHAKE_PIVOT_X
        view.pivotY = view.measuredHeight * SHAKE_PIVOT_Y

        val initialAnimation = createRotationAnimator(view, -SHAKE_ROTATION_ANGLE_DELTA)

        val repeatAnimation = createRotationAnimator(view, SHAKE_ROTATION_ANGLE_DELTA, -SHAKE_ROTATION_ANGLE_DELTA)
            .apply {
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
            }


        return AnimatorSet().apply {
            playSequentially(
                initialAnimation,
                repeatAnimation
            )
        }
    }

    private fun createEndShakeAnimator(view: View): AnimatorSet {
        return AnimatorSet().apply {
            play(createRotationAnimator(view, 0f))
        }
    }

    private fun createRotationAnimator(
        view: View,
        targetRotation: Float,
        startRotation: Float = view.rotation
    ): ValueAnimator {
        return ValueAnimator.ofFloat(startRotation, targetRotation)
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = SHAKE_ANIM_DURATION
                setTarget(view)
                addUpdateListener {
                    view.apply {
                        rotation = animatedValue as Float
                    }
                }
            }
    }
}
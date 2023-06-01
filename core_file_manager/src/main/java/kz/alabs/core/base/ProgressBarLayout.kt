package kz.alabs.core.base

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import kz.alabs.core_file_manager.R

class ProgressBarLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val sizeProgressBar = 80f
    private val elevationBg = 8f
    private var progressBarContainer: RelativeLayout? = null
    private val startAnim = 0f
    private val stopAnim = 1f
    private val progressBarPadding = 15

    override fun onFinishInflate() {
        super.onFinishInflate()
        progressBarContainer = RelativeLayout(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            ).apply {
                width = LayoutParams.MATCH_PARENT
                height = LayoutParams.MATCH_PARENT
                visibility = View.GONE
            }
        }

        val progressBar =
            ProgressBar(ContextThemeWrapper(context, R.style.LoadingProgressBarStyle)).apply {
                background = ContextCompat.getDrawable(context, R.drawable.bg_global_rounded_white)
                setPadding(progressBarPadding, progressBarPadding, progressBarPadding, progressBarPadding)

                layoutParams = LayoutParams(
                    context.toPixel(sizeProgressBar).toInt(),
                    context.toPixel(sizeProgressBar).toInt()
                ).apply {
                    addRule(CENTER_IN_PARENT)
                }
            }

        val notEmptyContainer = progressBarContainer ?: return

        notEmptyContainer.addView(progressBar)
        addView(notEmptyContainer)
        ViewCompat.setElevation(notEmptyContainer, elevationBg)
    }

    private fun makeAnimationLoader(start: Float, stop: Float): ValueAnimator {
        return ValueAnimator.ofFloat(start, stop).apply {
            addUpdateListener {
                val value = it.animatedValue as Float
                progressBarContainer?.alpha = value
            }
        }
    }

    fun showLoader() {
        if(progressBarContainer?.visibility != View.VISIBLE) {
            makeAnimationLoader(startAnim, stopAnim).apply {
                doOnStart {
                    progressBarContainer?.alpha = 0f
                    progressBarContainer?.visibility = View.VISIBLE
                    progressBarContainer?.isClickable = true
                }
            }.start()
        }
    }


    fun hideLoader() = makeAnimationLoader(stopAnim, startAnim).apply {
        doOnEnd {
            progressBarContainer?.visibility = View.GONE
            progressBarContainer?.isClickable = false
        }
    }.start()

    /*
    * Делаем для того чтобы при windowSoftInputMethod + fitSystemWindows в root layout-e не оверрайдил паддинги
    */
    override fun fitSystemWindows(insets: Rect?): Boolean {
        insets?.left = 0
        insets?.top = 0
        insets?.right = 0
        return super.fitSystemWindows(insets)
    }

}
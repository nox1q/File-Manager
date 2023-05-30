package kz.noxiq.core.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ParameterViewHolder<T : BaseGlobalLayoutParameter>(itemView: View) : CoreViewHolder<T>(itemView) {
    override fun bind(item: T) = with(itemView) {
        val lp = rootView.layoutParams as RecyclerView.LayoutParams
        rootView.layoutParams = lp.apply {
            setMargins(
                item.left.toInt(),
                item.top.toInt(),
                item.right.toInt(),
                item.bottom.toInt()
            )

            setPadding(
                item.paddingLeft?.toInt() ?: paddingLeft ?: 0,
                item.paddingTop?.toInt() ?: paddingTop ?: 0,
                item.paddingRight?.toInt() ?: paddingRight ?: 0,
                item.paddingBottom?.toInt() ?: paddingBottom ?: 0
            )
        }
    }
}
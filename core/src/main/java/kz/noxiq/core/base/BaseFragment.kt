package kz.noxiq.core.base

import androidx.annotation.LayoutRes

open class BaseFragment(
    @LayoutRes lay: Int,
) : CoreFragment(id = lay)
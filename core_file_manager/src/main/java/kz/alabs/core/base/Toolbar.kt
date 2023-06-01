package kz.alabs.core.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import kz.alabs.core_file_manager.R

class Toolbar : RelativeLayout {


    @DrawableRes
    private var leftIcon: Int? = null

    @DrawableRes
    private var menuIcon: Int? = null

    @DrawableRes
    private var shareIcon: Int? = null

    @DrawableRes
    private var searchIcon: Int? = null

    @DrawableRes
    private var additionalLeftIcon: Int? = null

    private var leftIconVisibility: Boolean? = null
    private var menuIconVisibility: Boolean? = null
    private var shareIconVisibility: Boolean? = null
    private var searchIconVisibility: Boolean? = null
    private var additionalLeftIconVisibility: Boolean? = null

    private var showBackIcon: Boolean? = null
    private var title: String? = null
    private var subtitle: String? = null
    private var textColor: Int? = -1
    private var subtitleTextColor: Int? = -1
    private var font: Int = -1
    private var subtitleFont: Int = -1
    private var textSizeValue: Float = 20f
    private var subtitleTextSize: Float = 12f
    private var titleInCenter: Boolean = false
    private var logoutViewGroupVisible = false
    private var badgeViewGroupVisible = false
    private var addtionalRightText: String? = null

    private var titleTv: TextView? = null
    private var subtitleTv: TextView? = null
    private var leftIconIv: ImageView? = null
    private var menuIconIv: ImageView? = null
    private var shareIconIv: ImageView? = null
    private var searchIconIv: ImageView? = null
    private var additionalLeftIconIv: ImageView? = null
    private var titleViewGroup: RelativeLayout? = null
    private var logoutViewGroup: RelativeLayout? = null
    private var additionalRightTextView: TextView? = null

    //badge's view
    private var badgeCounterTv: TextView? = null
    private var badgeTextLayout: LinearLayout? = null
    private var badgeViewGroup: ConstraintLayout? = null

    private var mContext: Context? = null

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {

        mContext = context
        initializeView(attrs, defStyleAttr)
    }

    private fun initializeView(attrs: AttributeSet?, defStyleAttr: Int) {
        if (mContext == null) return

        if (attrs != null && !isInEditMode) {
            val attributes: TypedArray = mContext?.theme?.obtainStyledAttributes(
                attrs, R.styleable.Toolbar,
                defStyleAttr, 0
            ) ?: return

            leftIcon =
                attributes.getResourceId(R.styleable.Toolbar_tb_leftIcon, 0)
            menuIcon =
                attributes.getResourceId(R.styleable.Toolbar_tb_menuIcon, 0)
            shareIcon =
                attributes.getResourceId(R.styleable.Toolbar_tb_shareIcon, 0)
            searchIcon =
                attributes.getResourceId(R.styleable.Toolbar_tb_searchIcon, 0)
            additionalLeftIcon =
                attributes.getResourceId(R.styleable.Toolbar_tb_additionalLeftIcon, 0)
            title =
                attributes.getString(R.styleable.Toolbar_tb_titleToolbar)
            subtitle =
                attributes.getString(R.styleable.Toolbar_tb_subtitleToolbar)
            leftIconVisibility =
                attributes.getBoolean(R.styleable.Toolbar_tb_leftIconVisibility, false)
            menuIconVisibility =
                attributes.getBoolean(R.styleable.Toolbar_tb_menuIconVisibility, false)
            shareIconVisibility =
                attributes.getBoolean(R.styleable.Toolbar_tb_shareIconVisibility, false)
            searchIconVisibility =
                attributes.getBoolean(R.styleable.Toolbar_tb_searchIconVisibility, false)
            additionalLeftIconVisibility =
                attributes.getBoolean(R.styleable.Toolbar_tb_additionalLeftIconVisibility, false)
            showBackIcon =
                attributes.getBoolean(R.styleable.Toolbar_tb_showBackIcon, false)
            textColor =
                attributes.getResourceId(R.styleable.Toolbar_tb_textColor, android.R.color.white)
            titleInCenter =
                attributes.getBoolean(R.styleable.Toolbar_tb_title_in_center, false)
            subtitleTextColor =
                attributes.getResourceId(
                    R.styleable.Toolbar_tb_subtitleTextColor,
                    android.R.color.black
                )
            font =
                attributes.getResourceId(R.styleable.Toolbar_tb_font, 0)
            subtitleFont =
                attributes.getResourceId(R.styleable.Toolbar_tb_subtitleFont, 0)
            textSizeValue =
                attributes.getDimension(R.styleable.Toolbar_tb_textSize, 20f)
            subtitleTextSize =
                attributes.getDimension(R.styleable.Toolbar_tb_subtitleTextSize, 0f)
            logoutViewGroupVisible =
                attributes.getBoolean(R.styleable.Toolbar_tb_logout_view_visible, false)
            badgeViewGroupVisible =
                attributes.getBoolean(R.styleable.Toolbar_tb_badge_view_visible, false)
            addtionalRightText = attributes.getString(R.styleable.Toolbar_tb_additionalRightText)

            attributes.recycle()

            val inflater =
                mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view =
                inflater.inflate(R.layout.view_toolbar, this) as Toolbar
            leftIconIv =
                view.findViewById(R.id.leftIconView) as ImageView
            titleTv =
                view.findViewById(R.id.toolbarTitle) as TextView
            badgeCounterTv =
                view.findViewById(R.id.tvBadgeCounter) as TextView
            subtitleTv =
                view.findViewById(R.id.toolbarSubtitle) as TextView
            menuIconIv =
                view.findViewById(R.id.menuIconView) as ImageView
            shareIconIv =
                view.findViewById(R.id.shareIconView) as ImageView
            searchIconIv =
                view.findViewById(R.id.searchIconView) as ImageView
            additionalLeftIconIv =
                view.findViewById(R.id.additionalLeftIconView) as ImageView
            titleViewGroup =
                view.findViewById(R.id.titleViewGroup) as RelativeLayout
            badgeTextLayout =
                view.findViewById(R.id.viewBadgeText) as LinearLayout
            badgeViewGroup =
                view.findViewById(R.id.badgeViewGroup) as ConstraintLayout
            additionalRightTextView =
                view.findViewById(R.id.additionalRightTextView) as TextView


            setupView()
        }
    }

    private fun setupView() {
        title?.let {
            setTitle(it)
        }

        textColor?.let {
            setTextColor(it)
        }

        logoutViewGroup?.let {
            it.isVisible = logoutViewGroupVisible
        }

        font.takeIf { it != 0 }?.let { setFont(it) }
        textSizeValue.takeIf { it != 0f }?.let { setTextSize(it) }

        subtitle?.let {
            setSubtitle(it)
            subtitleFont.takeIf { it != 0 }?.let { setSubtitleFont(it) }
            subtitleTextSize.takeIf { it != 0f }?.let { setSubtitleTextSize(it) }
            subtitleTextColor?.let { color ->
                setSubtitleTextColor(color)
            }
        }

        when (showBackIcon) {
            true -> showBackButton()
            else -> leftIcon.takeIf { it != 0 }?.let { setLeftIcon(it) }
        }

        menuIcon.takeIf { it != 0 }?.let { setMenuIcon(it) }
        shareIcon.takeIf { it != 0 }?.let { setShareIcon(it) }
        searchIcon.takeIf { it != 0 }?.let { setSearchIcon(it) }
        badgeViewGroupVisible.takeIf { it }?.let { showBadgeView() }

        addtionalRightText?.let {
            additionalRightTextView?.text = it
            additionalRightTextView?.isVisible = it.isNotBlank()
        }

    }

    fun getMenuIcon() = menuIconIv

    fun getShareIcon() = shareIconIv

    fun getSearchIcon() = searchIconIv

    fun getLeftIcon() = leftIconIv

    fun getLogoutViewGroup() = logoutViewGroup

    fun setTitle(text: String) {
        title = text
        titleTv?.let {
            it.text = text

            val layoutParams = it.layoutParams as LayoutParams
            val relativeLayoutParams = titleViewGroup?.layoutParams as LayoutParams

            if (titleInCenter) {
                layoutParams.addRule(CENTER_HORIZONTAL, TRUE)
                it.layoutParams = layoutParams

                relativeLayoutParams.addRule(END_OF, 0)
                relativeLayoutParams.addRule(START_OF, 0)
                titleViewGroup?.layoutParams = relativeLayoutParams
            } else {
                layoutParams.removeRule(CENTER_HORIZONTAL)
                it.layoutParams = layoutParams
            }
        }
    }

    fun setSubtitle(text: String) {
        subtitle = text
        subtitleTv?.let {
            it.text = text
            it.visibility = View.VISIBLE
        }
    }

    private fun setTextColor(color: Int) {
        titleTv?.let {
            it.textColor(color)
        }
    }

    private fun setSubtitleTextColor(color: Int) {
        subtitleTv?.let {
            it.textColor(color)
        }
    }

    private fun setTextSize(textSize: Float) {
        textSizeValue = textSize
        titleTv?.let {
            it.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeValue)
        }
    }

    private fun setSubtitleTextSize(textSize: Float) {
        subtitleTextSize = textSize
        subtitleTv?.let {
            it.setTextSize(TypedValue.COMPLEX_UNIT_SP, subtitleTextSize)
        }
    }

    private fun setFont(font: Int) {
        titleTv?.let {
            it.typeface = ResourcesCompat.getFont(context, font)
        }
    }

    private fun setSubtitleFont(font: Int) {
        subtitleTv?.let {
            it.typeface = ResourcesCompat.getFont(context, font)
        }
    }

    fun hideRightIcon() {
        menuIcon = null
        menuIconIv?.visibility = View.GONE
    }

    private fun showBackButton() {
        leftIconIv?.visibility = View.VISIBLE
    }

    private fun setLeftIcon(icon: Int) {
        this.leftIcon = icon
        leftIconIv?.let { iconTv ->
            leftIcon?.let {
                iconTv.setImageResource(it)
            }
            iconTv.visibility = View.VISIBLE
        }
    }

    fun hideLeftIcon() {
        leftIconIv?.isVisible = false
    }

    fun showLeftIcon() {
        leftIconIv?.isVisible = true
    }

    private fun setMenuIcon(icon: Int) {
        menuIcon = icon
        menuIconIv?.let {
            it.setImageResource(icon)
            it.visibility = View.VISIBLE
        }
    }

    private fun setShareIcon(icon: Int) {
        shareIcon = icon
        shareIconIv?.let {
            it.setImageResource(icon)
            it.visibility = View.VISIBLE
        }
    }

    private fun setSearchIcon(icon: Int) {
        searchIcon = icon
        searchIconIv?.let {
            it.setImageResource(icon)
            it.visibility = View.VISIBLE
        }
    }

    fun setAdditionalLeftIcon(icon: Int) {
        additionalLeftIcon = icon
        additionalLeftIconIv?.let {
            it.setImageResource(icon)
            it.visibility = View.VISIBLE
        }
    }

    private fun showBadgeView() {
        badgeViewGroup?.visibility = View.VISIBLE
    }


    fun setBadgeClickable(isClickable: Boolean) {
        badgeViewGroup?.isClickable = isClickable
    }

    fun doOnClickLeftIcon(block: () -> Unit) {
        leftIconIv?.setOnClickListener {
            block()
        }
    }

    fun doOnClickMenuIcon(block: () -> Unit) {
        menuIconIv?.setOnClickListener {
            block()
        }
    }

    fun doOnClickShareIcon(block: () -> Unit) {
        shareIconIv?.setOnClickListener {
            block()
        }
    }

    fun doOnClickSearchIcon(block: () -> Unit) {
        searchIconIv?.setOnClickListener {
            block()
        }
    }

    fun doOnClickLogoutViewGroup(block: () -> Unit) {
        logoutViewGroup?.setOnClickListener {
            block()
        }
    }

    fun doOnClickBadgeViewGroup(block: () -> Unit) {
        badgeViewGroup?.setOnClickListener {
            block()
        }
    }

    fun doOnClickRightText(block: () -> Unit) {
        additionalRightTextView?.setOnClickListener {
            block()
        }
    }
}
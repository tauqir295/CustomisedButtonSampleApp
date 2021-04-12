package com.example.mobile.design.lib

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use

class CustomizableGenericButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle) {

    private var customizedIv: ImageView? = null
    private var titleTv: TextView? = null
    private var subtitleTv: TextView? = null
    private var iconDrawable: Drawable? = null
    private var container: ConstraintLayout? = null

    var customizedButtonIconDrawable: Drawable?
        get() = iconDrawable
        set(value) {
            value?.let {
                iconDrawable = it
                customizedIv?.setImageDrawable(it)
            }
        }

    private var titleText: String?
        get() = titleTv?.text?.toString()
        set(value) {
            titleTv?.text = value
        }

    private var subtitleText: String?
        get() = subtitleTv?.text?.toString()
        set(value) {
            subtitleTv?.text = value
        }

    init {
        inflate(context, R.layout.customizable_generic_button, this)

        customizedIv = findViewById(R.id.customizableButtonIv)
        titleTv = findViewById(R.id.customizableButtonTitleTv)
        subtitleTv = findViewById(R.id.customizableButtonSubTitleTv)
        container = findViewById(R.id.customGenericButtonContainer)

        attrs?.let {
            initAttrs(it, defStyle)
        }
    }

    private fun initAttrs(attrs: AttributeSet, defStyle: Int) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomizedButton, defStyle, 0).use {

            iconDrawable = it.getDrawable(R.styleable.CustomizedButton_icon)

            titleText = it.getString(R.styleable.CustomizedButton_buttonTitleText)
            subtitleText = it.getString(R.styleable.CustomizedButton_buttonSubtitleText)

            customizedIv?.apply {
                visibility = if (it.getBoolean(R.styleable.CustomizedButton_iconVisible, false)) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }

            subtitleTv?.apply {
                if (it.getBoolean(R.styleable.CustomizedButton_subtitleVisible, false)) {
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE

//                    val density = context.resources.displayMetrics.density
//                    val paddingPixel = (8 * density).toInt()
////                    titleTv?.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel)
//
//
//                    val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//                    val margin = resources.getDimensionPixelSize(R.dimen.dimen_16dp)
//                    params.setMargins(margin, margin, margin, margin)
////                    titleTv?.layoutParams = params
                }
            }

            container?.apply {

                background = ContextCompat.getDrawable(context, it.getResourceId(R.styleable.CustomizedButton_backgroundImage, R.drawable.round_corner))
//                background = it.getDrawable(R.styleable.CustomizedButton_backgroundImage)
            }

        }
    }
}
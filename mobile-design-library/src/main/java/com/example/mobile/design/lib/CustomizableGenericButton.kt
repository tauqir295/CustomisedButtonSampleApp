package com.example.mobile.design.lib

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.use

class CustomizableGenericButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var customizedIv: ImageView? = null
    private var titleTv: TextView? = null
    private var subtitleTv: TextView? = null
    private var container: LinearLayout? = null

    var iconDrawable: Drawable?
        get() = customizedIv?.drawable
        set(drawable) {
            drawable?.let {
                customizedIv?.background = it
            }
        }

    var titleText: String?
        get() = titleTv?.text?.toString()
        set(text) {
            titleTv?.text = text
        }

    var subtitleText: String?
        get() = subtitleTv?.text?.toString()
        set(text) {
            subtitleTv?.text = text
        }

    var iconVisibility = false
        set(isVisible) {
            customizedIv?.visibility = if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
            field = false
        }

    var subtitleTvVisibility = false
        set(isVisible) {
            subtitleTv?.visibility = if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
            field = false
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

            titleText = it.getString(R.styleable.CustomizedButton_buttonTitleText)
            subtitleText = it.getString(R.styleable.CustomizedButton_buttonSubtitleText)

            customizedIv?.apply {
                iconDrawable = ResourcesCompat.getDrawable(
                    resources,
                    it.getResourceId(R.styleable.CustomizedButton_icon, R.drawable.round_corner),
                    context.theme
                )

                iconVisibility = it.getBoolean(R.styleable.CustomizedButton_iconVisible, false)

            }

            titleTv?.apply {
                setTextColor(
                    it.getColor(
                        R.styleable.CustomizedButton_buttonTitleTextColor,
                        context.getColorFromAttr(R.attr.defaultTextColor)
                    )
                )
            }

            subtitleTv?.apply {
                setTextColor(
                    it.getColor(
                        R.styleable.CustomizedButton_buttonSubtitleTextColor,
                        context.getColorFromAttr(R.attr.defaultTextColor)
                    )
                )
                subtitleTvVisibility = it.getBoolean(R.styleable.CustomizedButton_subtitleVisible, false)
            }

            container?.apply {
                background = ContextCompat.getDrawable(
                    context,
                    it.getResourceId(
                        R.styleable.CustomizedButton_backgroundImage,
                        R.drawable.round_corner
                    )
                )
            }
        }
    }

    /**
     * Update the view rendered. Use this in case of button is getting distorted
     */
    fun refreshView() {
        invalidate()
        requestLayout()
    }
}
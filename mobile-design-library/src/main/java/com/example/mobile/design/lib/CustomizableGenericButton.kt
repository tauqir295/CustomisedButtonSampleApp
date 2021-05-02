package com.example.mobile.design.lib

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.use

/**
 * Custom class for button
 */
class CustomizableGenericButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var customizedIv: ImageView? = null
    private var titleTv: TextView? = null
    private var subtitleTv: TextView? = null
    private var container: LinearLayout? = null

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
            field = isVisible
        }

    var subtitleTvVisibility = false
        set(isVisible) {
            subtitleTv?.visibility = if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
            field = isVisible
        }

    var buttonState = BUTTON_STATE_NONE
        set(value) {
            setBackgroundImage(value)
            field = value
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

    /**
     * initiate all the attributes here and set on respective view
     */
    private fun initAttrs(attrs: AttributeSet, defStyle: Int) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomizedButton, defStyle, 0).use {

            titleText = it.getString(R.styleable.CustomizedButton_buttonTitleText)
            subtitleText = it.getString(R.styleable.CustomizedButton_buttonSubtitleText)

            customizedIv?.apply {
                background = ResourcesCompat.getDrawable(
                    resources,
                    it.getResourceId(R.styleable.CustomizedButton_icon, R.drawable.ic_user),
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
                subtitleTvVisibility =
                    it.getBoolean(R.styleable.CustomizedButton_subtitleVisible, false)
            }

            buttonState = it.getInteger(R.styleable.CustomizedButton_buttonState, 0)

        }
    }

    /**
     * set button image based on state
     * @param - [value] - use it for different state drawable
     */
    private fun setBackgroundImage(value: Int) {
        container?.apply {
            background = when (value) {
                BUTTON_STATE_NONE -> {
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.round_corner,
                        context.resources.newTheme()
                    )
                }
                BUTTON_STATE_DISABLED -> {
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.button_faded,
                        context.resources.newTheme()
                    )
                }
                else -> {
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.button_selector,
                        context.resources.newTheme()
                    )
                }
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

    /**
     * saving button state
     */
    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putString(TITLE_TEXT, titleText)
            putString(SUBTITLE_TEXT, subtitleText)
            putInt(BUTTON_STATE, buttonState)
            putBoolean(ICON_VISIBILITY, iconVisibility)
            putBoolean(SUBTITLE_TV_VISIBILITY, subtitleTvVisibility)
            putParcelable(SUPER_STATE, super.onSaveInstanceState())
        }
    }

    /**
     * restoring button state
     */
    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            state.run {
                titleText = getString(TITLE_TEXT, "")
                subtitleText = getString(SUBTITLE_TEXT, "")
                buttonState = getInt(BUTTON_STATE, 0)
                iconVisibility = getBoolean(ICON_VISIBILITY, false)
                subtitleTvVisibility = getBoolean(SUBTITLE_TV_VISIBILITY, false)
                super.onRestoreInstanceState(getParcelable(SUPER_STATE))
            }
        }
    }

    companion object {
        const val BUTTON_STATE_NONE = 0
        const val BUTTON_STATE_DISABLED = 1
        const val BUTTON_STATE_ENABLED = 2
        const val TITLE_TEXT = "titleText"
        const val SUBTITLE_TEXT = "subtitleText"
        const val BUTTON_STATE = "buttonState"
        const val ICON_VISIBILITY = "iconVisibility"
        const val SUBTITLE_TV_VISIBILITY = "subtitleTvVisibility"
        const val SUPER_STATE = "superState"
    }
}
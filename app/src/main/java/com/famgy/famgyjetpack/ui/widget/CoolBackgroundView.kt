package com.famgy.famgyjetpack.ui.widget

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.famgy.famgyjetpack.R

class CoolBackgroundView : View {

    private lateinit var drawable: Drawable

    constructor(context: Context):super(context) {
    }

    constructor(context: Context, attributeSet: AttributeSet):super(context, attributeSet) {
        initView(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int):super(context, attributeSet, defStyleAttr) {
        initView(context, attributeSet)
    }

    private fun initView(context: Context, attributeSet: AttributeSet) {
        var typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingBackgroundView)
        try {
            var e = typedArray.getDimensionPixelSize(R.styleable.ScrollingBackgroundView_sbv_scrollX, 0)
            var f = typedArray.getDimensionPixelSize(R.styleable.ScrollingBackgroundView_sbv_scrollY, 0)
            drawable = typedArray.getDrawable(R.styleable.ScrollingBackgroundView_sbv_drawable)

            setDrawable();
        } finally {
            typedArray.recycle()
        }
    }

    private fun setDrawable() {
        var x = 0
        var y = 0
        var width = context.resources.displayMetrics.widthPixels
        var height = drawable.intrinsicHeight

        drawable.setBounds(x, y, x + width, y + height)

        setWillNotDraw(false)
        postInvalidateOnAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawable.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        resizeImage(resources, R.styleable.ScrollingBackgroundView_sbv_drawable, w, h)
    }

    private fun resizeImage(res: Resources, resId: Int, w: Int, h: Int) {
        var option = BitmapFactory.Options()

        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, option)
        option.inSampleSize = Math.round(drawable.intrinsicWidth.toFloat() / w.toFloat())
        option.inJustDecodeBounds = true

        BitmapFactory.decodeResource(res, resId, option)
    }
}
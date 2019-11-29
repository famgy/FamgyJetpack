package com.famgy.famgyjetpack.ui.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.famgy.famgyjetpack.R

class CoolBackgroundView : View {

    private lateinit var drawable: Drawable
    private  var c: Int = 0
    private  var e: Int = 0
    private  var f: Int = 0

    constructor(context: Context):super(context) {
    }

    constructor(context: Context, attributeSet: AttributeSet):super(context, attributeSet) {
        initView(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int):super(context, attributeSet, defStyleAttr) {
        initView(context, attributeSet)
    }

    private fun initView(context: Context, attributeSet: AttributeSet) {
        c = context.resources.displayMetrics.widthPixels

        var typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingBackgroundView)
        try {
            e = typedArray.getDimensionPixelSize(R.styleable.ScrollingBackgroundView_sbv_scrollX, 0)
            f = typedArray.getDimensionPixelSize(R.styleable.ScrollingBackgroundView_sbv_scrollY, 0)
            drawable = typedArray.getDrawable(R.styleable.ScrollingBackgroundView_sbv_drawable)!!

            setDrawable();
        } finally {
            typedArray.recycle()
        }
    }

    private fun setDrawable() {
        var proportion = c.toFloat() / drawable.intrinsicWidth.toFloat()
        var height = drawable.intrinsicHeight.toFloat()
        var bottom: Int = (proportion * height).toInt()

        drawable.setBounds(0, 0, c, bottom)

        setWillNotDraw(false)
        postInvalidateOnAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //drawable.draw(canvas)

        var scrollX = e
        var scrollY = f
        var width = canvas?.width as Int
        var height = canvas?.height as Int
        var stepLen = 2.0f

        var rect = drawable.bounds

        var drawableWidth = rect.width()
        var drawableHeight = rect.height()

        var startX = moveScroll(scrollX, drawableWidth)
        var iterationsX = moveTotalScroll(width, startX, drawableWidth)

        val startY = moveScroll(scrollY, drawableHeight)
        val iterationsY = moveTotalScroll(height, startY, drawableHeight)

        val save = canvas.save()

        canvas.translate(startX.toFloat(), startY.toFloat())
        for (x in 0..iterationsX) {
            for (y in 0..iterationsY) {
                drawable.draw(canvas)
                canvas.translate(0.0f, drawableHeight.toFloat())
            }
            canvas.translate(drawableWidth.toFloat(), (-(drawableHeight * iterationsY)).toFloat())
        }

        canvas.restoreToCount(save);

        f = (f.toFloat() + Math.abs(stepLen)).toInt()

        postInvalidateOnAnimation();
    }

    private fun moveScroll(scroll: Int, side: Int): Int {
        var modulo = Math.abs(scroll) % side
        if (0 == modulo) {
            return 0
        }

        if (scroll < 0) {
            return -(side - modulo)
        }

        return -modulo
    }

    private fun moveTotalScroll(total: Int, start: Int, side: Int): Int {
        var diff = total - start
        return (if (diff % side > 0) 1 else 0) + diff / side
    }
}
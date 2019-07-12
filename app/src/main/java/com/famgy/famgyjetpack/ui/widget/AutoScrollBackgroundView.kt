package com.famgy.famgyjetpack.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat.*
import com.famgy.famgyjetpack.R




class AutoScrollBackgroundView : View {

    private  var c: Int = 0
    private  var e: Int = 0
    private  var f: Int = 0
    private lateinit var d: Drawable
    private var imgSrcId: Int = 0

    constructor(context: Context, attrs:AttributeSet):super(context, attrs) {

        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        c = context.resources.displayMetrics.widthPixels

        var typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollingBackgroundView)
        try {
            e = typedArray.getDimensionPixelSize(R.styleable.ScrollingBackgroundView_sbv_scrollX, 0)
            f = typedArray.getDimensionPixelSize(R.styleable.ScrollingBackgroundView_sbv_scrollY, 0)
            imgSrcId = typedArray.getResourceId(R.styleable.ScrollingBackgroundView_sbv_drawable, -1)
            var tmp: Drawable? = getDrawable(getResources(), imgSrcId, null);
            if (null != tmp) {
                setDrawable(tmp);
            }
        } finally {
            typedArray.recycle()
        }
    }

    private fun setDrawable(drawable: Drawable) {
        d = drawable

        if (null != d) {
            var rect: Rect = d.bounds
            if (null == rect || rect.isEmpty) {
                var proportion = c.toFloat() / d.intrinsicWidth.toFloat()
                var height = d.intrinsicHeight.toFloat()
                var bottom: Int = (proportion * height).toInt()

                d.setBounds(0, 0, c, bottom)
            }
            setWillNotDraw(false)
        }

        postInvalidateOnAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var drawable = d
        if (drawable != null) {
            var scrollX = e
            var scrollY = f
            var width = canvas?.width as Int
            var height = canvas?.height as Int

            var rect = drawable.bounds

            var drawableWidth = rect.width()
            var drawableHeight = rect.height()

            var startX = moveScroll(scrollX, drawableWidth)
            var iterationsX = moveTotalScroll(width, startX, drawableWidth)

            val startY = moveScroll(scrollY, drawableHeight)
            val iterationsY = moveTotalScroll(height, startY, drawableHeight)

            val save = canvas.save()

            try {
                canvas.translate(startX as Float, startY as Float)
                for (x in 0..iterationsX) {
                    for (y in 0..iterationsY) {
                        drawable.draw(canvas)
                        canvas.translate(0.0f, drawableHeight as Float)
                    }
                    canvas.translate(drawableWidth as Float, (-(drawableHeight * iterationsY)) as Float)
                }
            } finally {
                canvas.restoreToCount(save)
            }
        }
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
package com.famgy.famgyjetpack.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.AnimationDrawable
import kotlinx.android.synthetic.main.activity_splash.*
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import com.famgy.famgyjetpack.R

class SplashActivity : AppCompatActivity() {

    private lateinit var valueAnimator: ValueAnimator
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var mediaPlayer: MediaPlayer
    private var handler = Handler()
    private var runnable = Runnable {
        mediaPlayer.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initValueAnimation()
        initDrawableAnimation()
        initMediaPlayer()
    }

    private fun initValueAnimation() {
        splash_text.getDrawable().setAlpha(0)

        valueAnimator = ValueAnimator.ofInt(0, 255)
        valueAnimator.duration = 1200
        valueAnimator.startDelay = 800
        valueAnimator.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
            val value = (valueAnimator.animatedValue as Int).toInt()
            splash_text?.getDrawable()?.setAlpha(value)
            if (value == 255) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun initDrawableAnimation() {
        iv_img.setImageResource(R.drawable.amini_splash)
        animationDrawable = iv_img.drawable as AnimationDrawable
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.sound)
        mediaPlayer.setVolume(0.6f, 0.6f)
    }

    override fun onResume() {
        super.onResume()

        valueAnimator.start()
        animationDrawable.start()
        handler.postDelayed(runnable, 1200)
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
        valueAnimator.cancel()
        animationDrawable.stop()
    }
}

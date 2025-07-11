package com.example.indicator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var leftindicatorstatus = false
    private var rightindicatorstatus = false

    private lateinit var imgArrow1: ImageView
    private lateinit var imgArrow2: ImageView
    private lateinit var btnLeft: Button
    private lateinit var btnRight: Button

    private val handler = Handler(Looper.getMainLooper())
    private var blinking = false
    private var activeArrow: ImageView? = null

    private val blinkRunnable = object : Runnable {
        private var visible = true
        override fun run() {
            if (!blinking || activeArrow == null) {
                imgArrow1.visibility = View.INVISIBLE
                imgArrow2.visibility = View.INVISIBLE
                return
            }

            activeArrow?.visibility = if (visible) View.VISIBLE else View.INVISIBLE
            visible = !visible
            handler.postDelayed(this, 500)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgArrow1 = findViewById(R.id.imgArrow1)
        imgArrow2 = findViewById(R.id.imgArrow2)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)

        imgArrow1.visibility = View.INVISIBLE
        imgArrow2.visibility = View.INVISIBLE

        btnLeft.setOnClickListener {
            if (!leftindicatorstatus) {
                startBlinking(imgArrow1)
                leftindicatorstatus = true
                rightindicatorstatus = false
            } else {
                stopBlinking()
                leftindicatorstatus = false
            }
        }

        btnRight.setOnClickListener {
            if (!rightindicatorstatus) {
                startBlinking(imgArrow2)
                rightindicatorstatus = true
                leftindicatorstatus = false
            } else {
                stopBlinking()
                rightindicatorstatus = false
            }
        }
    }

    private fun startBlinking(arrow: ImageView) {
        blinking = true
        activeArrow = arrow

        imgArrow1.visibility = View.INVISIBLE
        imgArrow2.visibility = View.INVISIBLE
        arrow.visibility = View.VISIBLE

        handler.removeCallbacks(blinkRunnable)
        handler.post(blinkRunnable)
    }

    private fun stopBlinking() {
        blinking = false
        handler.removeCallbacks(blinkRunnable)
        imgArrow1.visibility = View.INVISIBLE
        imgArrow2.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }
}

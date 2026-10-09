package com.yeosangist.sparkles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.Choreographer
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class SparkleView(context: Context) : View(context) {
    private val sparkles = mutableListOf<Sparkle>()
    private var isRunning = false
    private val choreographer = Choreographer.getInstance()
    private val frameCallback = FrameCallback()
    
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    init {
        alpha = 1f
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            regenerateSparkles(w, h)
        }
    }

    fun start() {
        if (!isRunning) {
            isRunning = true
            choreographer.postFrameCallback(frameCallback)
        }
    }

    fun stop() {
        isRunning = false
        choreographer.removeFrameCallback(frameCallback)
    }

    private fun regenerateSparkles(width: Int, height: Int) {
        sparkles.clear()
        repeat(SparkleConfig.sparkleCount) {
            sparkles.add(Sparkle.create(width, height))
        }
    }

    private inner class FrameCallback : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            if (!isRunning) return
            
            updateSparkles()
            invalidate()
            choreographer.postFrameCallback(this)
        }
    }

    private fun updateSparkles() {
        val currentTime = System.currentTimeMillis()
        val width = width
        val height = height

        sparkles.removeAll { !it.isAlive(currentTime) }

        while (sparkles.size < SparkleConfig.sparkleCount) {
            sparkles.add(Sparkle.create(width, height))
        }

        sparkles.forEach { it.updatePosition(currentTime) }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        if (sparkles.isEmpty()) return

        val currentTime = System.currentTimeMillis()

        sparkles.forEach { sparkle ->
            if (!sparkle.isAlive(currentTime)) return@forEach

            val alpha = sparkle.getAlpha(currentTime)
            val ageSeconds = (currentTime - sparkle.birthTime) / 1000f
            val size = sparkle.size * (1f + kotlin.math.sin(
                ageSeconds * 3f + sparkle.phase
            ) * 0.1f)
            val rotation = sparkle.getRotation(currentTime)

            drawSparkle(canvas, sparkle, size, alpha, rotation)
        }
    }

    private fun drawSparkle(
        canvas: Canvas,
        sparkle: Sparkle,
        size: Float,
        alpha: Float,
        rotation: Float
    ) {
        canvas.save()
        canvas.translate(sparkle.x, sparkle.y)
        canvas.rotate(rotation)

        paint.color = sparkle.color
        paint.alpha = (alpha * 255).toInt()
        if (sparkle.type == SparkleType.HOLLOW_DIAMOND) {
            drawHollowDiamond(canvas, size)
        } else {
            drawShape(canvas, sparkle.type, size)
        }

        canvas.restore()
    }

    private fun drawShape(canvas: Canvas, type: SparkleType, size: Float) {
        paint.style = Paint.Style.FILL
        val path = createPathForType(type, size)
        canvas.drawPath(path, paint)
    }

    private fun drawHollowDiamond(canvas: Canvas, size: Float) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = maxOf(0.7f, size * 0.22f)
        
        val path = Path()
        path.moveTo(0f, -size * 2f)
        path.lineTo(size * 0.35f, -size * 0.35f)
        path.lineTo(size * 2f, 0f)
        path.lineTo(size * 0.35f, size * 0.35f)
        path.lineTo(0f, size * 2f)
        path.lineTo(-size * 0.35f, size * 0.35f)
        path.lineTo(-size * 2f, 0f)
        path.lineTo(-size * 0.35f, -size * 0.35f)
        path.close()
        
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.FILL
    }

    private fun createPathForType(type: SparkleType, size: Float): Path {
        val path = Path()

        when (type) {
            SparkleType.DIAMOND -> {
                path.moveTo(0f, -size * 2f)
                path.lineTo(size * 0.35f, -size * 0.35f)
                path.lineTo(size * 2f, 0f)
                path.lineTo(size * 0.35f, size * 0.35f)
                path.lineTo(0f, size * 2f)
                path.lineTo(-size * 0.35f, size * 0.35f)
                path.lineTo(-size * 2f, 0f)
                path.lineTo(-size * 0.35f, -size * 0.35f)
                path.close()
            }
            SparkleType.HOLLOW_DIAMOND -> {
                path.moveTo(0f, -size * 2f)
                path.lineTo(size * 0.35f, -size * 0.35f)
                path.lineTo(size * 2f, 0f)
                path.lineTo(size * 0.35f, size * 0.35f)
                path.lineTo(0f, size * 2f)
                path.lineTo(-size * 0.35f, size * 0.35f)
                path.lineTo(-size * 2f, 0f)
                path.lineTo(-size * 0.35f, -size * 0.35f)
                path.close()
            }
            SparkleType.SOFT_STAR -> {
                val points = 5
                val innerRadius = size * 0.42f
                val outerRadius = size * 1.8f
                for (i in 0 until points * 2) {
                    val radius = if (i % 2 == 0) outerRadius else innerRadius
                    val angle = (i * Math.PI / points) - Math.PI / 2
                    val x = cos(angle).toFloat() * radius
                    val y = sin(angle).toFloat() * radius
                    if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                path.close()
            }
            SparkleType.SIX_POINT -> {
                val points = 6
                val innerRadius = size * 0.38f
                val outerRadius = size * 1.8f
                for (i in 0 until points * 2) {
                    val radius = if (i % 2 == 0) outerRadius else innerRadius
                    val angle = (i * Math.PI / points) - Math.PI / 2
                    val x = cos(angle).toFloat() * radius
                    val y = sin(angle).toFloat() * radius
                    if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                path.close()
            }
            SparkleType.EIGHT_POINT -> {
                val points = 8
                val innerRadius = size * 0.32f
                val outerRadius = size * 1.9f
                for (i in 0 until points * 2) {
                    val radius = if (i % 2 == 0) outerRadius else innerRadius
                    val angle = (i * Math.PI / points) - Math.PI / 2
                    val x = cos(angle).toFloat() * radius
                    val y = sin(angle).toFloat() * radius
                    if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                path.close()
            }
        }

        return path
    }
}

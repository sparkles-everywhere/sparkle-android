package com.yeosangist.sparkles

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.cos
import kotlin.math.sin

object SparkleRenderer {
    private val pathCache = mutableMapOf<SparkleType, Path>()
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    fun drawSparkle(
        canvas: Canvas,
        sparkle: Sparkle,
        size: Float,
        alpha: Float,
        isGlow: Boolean = false
    ) {
        val path = getPathForType(sparkle.type)
        
        canvas.save()
        canvas.translate(sparkle.x, sparkle.y)
        canvas.rotate(sparkle.getRotation(System.currentTimeMillis()))
        canvas.scale(size, size)
        
        paint.color = sparkle.color
        paint.alpha = (alpha * 255).toInt()
        canvas.drawPath(path, paint)
        
        canvas.restore()
    }

    private fun getPathForType(type: SparkleType): Path {
        return pathCache.getOrPut(type) { createPathForType(type) }
    }

    private fun createPathForType(type: SparkleType): Path {
        val path = Path()
        
        when (type) {
            SparkleType.DIAMOND -> {
                path.moveTo(0f, -1f)
                path.lineTo(0.7f, 0f)
                path.lineTo(0f, 1f)
                path.lineTo(-0.7f, 0f)
                path.close()
            }
            SparkleType.HOLLOW_DIAMOND -> {
                path.moveTo(0f, -1f)
                path.lineTo(0.7f, 0f)
                path.lineTo(0f, 1f)
                path.lineTo(-0.7f, 0f)
                path.close()
            }
            SparkleType.SOFT_STAR -> {
                val points = 4
                val innerRadius = 0.4f
                val outerRadius = 1f
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
                val innerRadius = 0.3f
                val outerRadius = 1f
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
                val innerRadius = 0.35f
                val outerRadius = 1f
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

    fun drawHollowDiamond(canvas: Canvas, size: Float, alpha: Float) {
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = size * 0.15f
            this.alpha = (alpha * 255).toInt()
        }
        
        val path = getPathForType(SparkleType.HOLLOW_DIAMOND)
        val matrix = android.graphics.Matrix()
        matrix.setScale(size, size)
        path.transform(matrix)
        
        canvas.drawPath(path, paint)
        
        paint.style = Paint.Style.FILL
    }

    fun clearCache() {
        pathCache.clear()
    }
}

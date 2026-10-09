package com.yeosangist.sparkles

import kotlin.random.Random

enum class SparkleType {
    DIAMOND,
    HOLLOW_DIAMOND,
    SOFT_STAR,
    SIX_POINT,
    EIGHT_POINT
}

data class Sparkle(
    var baseX: Float,
    var baseY: Float,
    var x: Float,
    var y: Float,
    var size: Float,
    var birthTime: Long,
    var lifetime: Long,
    var phase: Float,
    var wiggleSpeedX: Float,
    var wiggleSpeedY: Float,
    var rotationPhase: Float,
    var rotationSpeed: Float,
    var type: SparkleType,
    var color: Int
) {
    companion object {
        fun create(width: Int, height: Int): Sparkle {
            val random = Random
            
            val color = if (SparkleConfig.selectedColorPreset.isRainbow) {
                SparkleConfig.selectedColorPreset.rainbowColors[random.nextInt(SparkleConfig.selectedColorPreset.rainbowColors.size)]
            } else {
                SparkleConfig.selectedColorPreset.color
            }
            
            return Sparkle(
                baseX = random.nextFloat() * width,
                baseY = random.nextFloat() * height,
                x = 0f,
                y = 0f,
                size = SparkleConfig.minSize + random.nextFloat() * (SparkleConfig.maxSize - SparkleConfig.minSize),
                birthTime = System.currentTimeMillis(),
                lifetime = SparkleConfig.minLifetime + random.nextLong(SparkleConfig.maxLifetime - SparkleConfig.minLifetime),
                phase = random.nextFloat() * Math.PI.toFloat() * 2f,
                wiggleSpeedX = 0.5f + random.nextFloat() * 1.5f,
                wiggleSpeedY = 0.5f + random.nextFloat() * 1.5f,
                rotationPhase = random.nextFloat() * Math.PI.toFloat() * 2f,
                rotationSpeed = 0.4f + random.nextFloat() * 0.8f,
                type = SparkleType.entries[random.nextInt(SparkleType.entries.size)],
                color = color
            )
        }
    }

    fun isAlive(currentTime: Long): Boolean {
        return currentTime - birthTime < lifetime
    }

    fun getAlpha(currentTime: Long): Float {
        val age = currentTime - birthTime
        val progress = age.toFloat() / lifetime.toFloat()

        val fadeIn = (progress * 5f).coerceAtMost(1f)
        val fadeOut = ((1f - progress) * 5f).coerceAtMost(1f)

        return fadeIn * fadeOut
    }

    fun updatePosition(currentTime: Long) {
        val time = (currentTime - birthTime) / 1000f
        x = baseX + kotlin.math.sin(time * wiggleSpeedX + phase) * SparkleConfig.wiggleDistance
        y = baseY + kotlin.math.sin(time * wiggleSpeedY + phase * 1.37f) * SparkleConfig.wiggleDistance
    }

    fun getRotation(currentTime: Long): Float {
        val time = (currentTime - birthTime) / 1000f
        return kotlin.math.sin(time * rotationSpeed + rotationPhase) * SparkleConfig.maxRotation
    }

}

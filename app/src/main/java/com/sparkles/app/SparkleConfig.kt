package com.yeosangist.sparkles

import android.content.Context
import android.graphics.Color

data class ColorPreset(
    val name: String?,
    val color: Int,
    val isRainbow: Boolean = false,
    val rainbowColors: List<Int> = emptyList(),
    val useTextDisplay: Boolean = false
) {
    companion object {
        val BLACK = ColorPreset("Black", Color.BLACK, useTextDisplay = true)
        val WHITE = ColorPreset("White", Color.WHITE, useTextDisplay = true)
        val GREY = ColorPreset("Grey", Color.parseColor("#888888"), useTextDisplay = true)
        val RED = ColorPreset("Red", Color.parseColor("#FF0000"), useTextDisplay = true)
        val ORANGE = ColorPreset("Orange", Color.parseColor("#FF8800"), useTextDisplay = true)
        val YELLOW = ColorPreset("Yellow", Color.parseColor("#FFFF00"), useTextDisplay = true)
        val GREEN = ColorPreset("Green", Color.parseColor("#00FF00"), useTextDisplay = true)
        val BLUE = ColorPreset("Blue", Color.parseColor("#0000FF"), useTextDisplay = true)
        val PURPLE = ColorPreset("Purple", Color.parseColor("#880088"), useTextDisplay = true)
        val PINK = ColorPreset("Pink", Color.parseColor("#FF00FF"), useTextDisplay = true)
        val RAINBOW = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF0000"),
                Color.parseColor("#FF8800"),
                Color.parseColor("#FFFF00"),
                Color.parseColor("#00FF00"),
                Color.parseColor("#0000FF"),
                Color.parseColor("#880088"),
                Color.parseColor("#FF00FF")
            )
        )
        
        // Flag presets from flags.js
        val GAY_MEN = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#078D70"),
                Color.parseColor("#26CEAA"),
                Color.parseColor("#98E8C1"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#7BADE2"),
                Color.parseColor("#5049CC"),
                Color.parseColor("#3D1A78")
            )
        )
        
        val LESBIAN = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#D52D00"),
                Color.parseColor("#EF7627"),
                Color.parseColor("#FF9A56"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#D162A4"),
                Color.parseColor("#B55690"),
                Color.parseColor("#A30262")
            )
        )
        
        val BISEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#D60270"),
                Color.parseColor("#9B4F96"),
                Color.parseColor("#0038A8")
            )
        )
        
        val PANSEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF218C"),
                Color.parseColor("#FFD800"),
                Color.parseColor("#21B1FF")
            )
        )
        
        val TRANSGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#5BCEFA"),
                Color.parseColor("#F5A9B8"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val NON_BINARY = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FCF434"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#9C59D1"),
                Color.parseColor("#000000")
            )
        )
        
        val ASEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#000000"),
                Color.parseColor("#A3A3A3"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#800080")
            )
        )
        
        val AROMANTIC = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#3DA542"),
                Color.parseColor("#A7D379"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#A9A9A9"),
                Color.parseColor("#000000")
            )
        )
        
        val AROACE = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#DD8A00"),
                Color.parseColor("#E9CC07"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#65B0DD"),
                Color.parseColor("#213C57")
            )
        )
        
        val DEMISEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#000000"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#6E0070"),
                Color.parseColor("#D2D2D2")
            )
        )
        
        val GENDERFLUID = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF75A2"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#BE18D6"),
                Color.parseColor("#000000"),
                Color.parseColor("#333EBD")
            )
        )
        
        val GENDERQUEER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#B57EDC"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#4A8123")
            )
        )
        
        val AGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#000000"),
                Color.parseColor("#B9B9B9"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#B8F483")
            )
        )
        
        val BIGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#C479D9"),
                Color.parseColor("#EDA5CD"),
                Color.parseColor("#D8D8D8"),
                Color.parseColor("#A4E8D8"),
                Color.parseColor("#6ADEC9")
            )
        )
        
        val PANGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#fdf48d"),
                Color.parseColor("#f3b79c"),
                Color.parseColor("#fac3ef"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val OMNISEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF9A4D"),
                Color.parseColor("#FF53BF"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#625FFF"),
                Color.parseColor("#1F9BFF")
            )
        )
        
        val POLYSEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#F61CB9"),
                Color.parseColor("#07D569"),
                Color.parseColor("#1C92F5")
            )
        )
        
        val INTERSEX = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FFD800"),
                Color.parseColor("#7902AA")
            )
        )
        
        val TWO_SPIRIT = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#D62828"),
                Color.parseColor("#F77F00"),
                Color.parseColor("#FCBF49"),
                Color.parseColor("#2A9D8F"),
                Color.parseColor("#277DA1"),
                Color.parseColor("#7B2CBF")
            )
        )
        
        val SAPPIC = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF8DC7"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#D629A9"),
                Color.parseColor("#7B1FA2")
            )
        )
        
        val QUESTIONING = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF75A2"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#9C59D1"),
                Color.parseColor("#000000"),
                Color.parseColor("#5BCEFA")
            )
        )
        
        val POLYAMOROUS = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#009FE3"),
                Color.parseColor("#E50051"),
                Color.parseColor("#340C46"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#FCBF00")
            )
        )
        
        val ABROSEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#46D294"),
                Color.parseColor("#A3E9C8"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#F5A9B8"),
                Color.parseColor("#EE1766")
            )
        )
        
        val GRAYSEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#740195"),
                Color.parseColor("#B2B2B2"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val GRAYROMANTIC = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#087D16"),
                Color.parseColor("#B2B2B2"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val DEMIGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#7F7F7F"),
                Color.parseColor("#C4C4C4"),
                Color.parseColor("#FFEE70"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val DEMIBOY = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#7F7F7F"),
                Color.parseColor("#C4C4C4"),
                Color.parseColor("#9AD9EB"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val DEMIGIRL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#7F7F7F"),
                Color.parseColor("#C4C4C4"),
                Color.parseColor("#FFAEC9"),
                Color.parseColor("#FFFFFF")
            )
        )
        
        val GENDERFLUX = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#F47694"),
                Color.parseColor("#F2A3B9"),
                Color.parseColor("#CECECE"),
                Color.parseColor("#7CE0F7"),
                Color.parseColor("#3ECDF9"),
                Color.parseColor("#FFF48E")
            )
        )
        
        val BIGENDER_ALT = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#C479A2"),
                Color.parseColor("#EDA5CD"),
                Color.parseColor("#D6C7E8"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#9AC7E8"),
                Color.parseColor("#6D82D1")
            )
        )
        
        val GENDERFAE = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#97C3A5"),
                Color.parseColor("#C3DEAE"),
                Color.parseColor("#F9FACD"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#FCA2C4"),
                Color.parseColor("#DB8AE4"),
                Color.parseColor("#A97EDD")
            )
        )
        
        val GENDERFAUN = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FCD689"),
                Color.parseColor("#FFF09B"),
                Color.parseColor("#FAF9CD"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#8EDED9"),
                Color.parseColor("#8CACDE"),
                Color.parseColor("#9782EC")
            )
        )
        
        val XENOGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF6691"),
                Color.parseColor("#FF9997"),
                Color.parseColor("#FFB782"),
                Color.parseColor("#FBFFA6"),
                Color.parseColor("#84BBFF"),
                Color.parseColor("#9C84FF"),
                Color.parseColor("#A317FF")
            )
        )
        
        val LITHROMANTIC = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#7CBE42"),
                Color.parseColor("#FDEE23"),
                Color.parseColor("#A2A2A2")
            )
        )
        
        val FRAYSEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#226CB5"),
                Color.parseColor("#93E7DD"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#636363")
            )
        )
        
        val CUPIOSEXUAL = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#A0A0A0"),
                Color.parseColor("#C8BFE6"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#FFB3DA")
            )
        )
        
        val CUPIOROMANTIC = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FCA9A3"),
                Color.parseColor("#FDC5C0"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#C8BFE6"),
                Color.parseColor("#A0A0A0")
            )
        )
        
        val TRIGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FF76A4"),
                Color.parseColor("#FFB3CB"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#3DA542"),
                Color.parseColor("#9AC7E8"),
                Color.parseColor("#6D82D1"),
                Color.parseColor("#9C59D1")
            )
        )
        
        val MULTIGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#3F47CD"),
                Color.parseColor("#00A3E8"),
                Color.parseColor("#FA7F27")
            )
        )
        
        val POLYGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#000000"),
                Color.parseColor("#8FA6BF"),
                Color.parseColor("#E875A8"),
                Color.parseColor("#F4E64D"),
                Color.parseColor("#39A9E8")
            )
        )
        
        val ANDROGYNE = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FE007F"),
                Color.parseColor("#9A00FF"),
                Color.parseColor("#00B8E7")
            )
        )
        
        val NEUTROIS = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#1F9B00"),
                Color.parseColor("#000000")
            )
        )
        
        val MAVERIQUE = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FFF344"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#F49622")
            )
        )
        
        val OMNIGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#F4A6C1"),
                Color.parseColor("#C8C4E2"),
                Color.parseColor("#A94BA8"),
                Color.parseColor("#7194C4"),
                Color.parseColor("#9AD8E8")
            )
        )
        
        val APORAGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#F5A6C8"),
                Color.parseColor("#9A8AE8"),
                Color.parseColor("#F4D44D"),
                Color.parseColor("#7F9FE8")
            )
        )
        
        val GENDERVOID = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#0B164F"),
                Color.parseColor("#4A4A4A"),
                Color.parseColor("#000000")
            )
        )
        
        val GREYGENDER = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#ABABAB"),
                Color.parseColor("#3D3D3D"),
                Color.parseColor("#9B59B6"),
                Color.parseColor("#000000")
            )
        )
        
        val QUOIROMANTIC = ColorPreset(
            null,
            Color.WHITE,
            isRainbow = true,
            rainbowColors = listOf(
                Color.parseColor("#000000"),
                Color.parseColor("#8BCF45"),
                Color.parseColor("#55C7D9"),
                Color.parseColor("#A4A4A4")
            )
        )
        
        val DEFAULT_PRESETS = listOf(
            BLACK, WHITE, GREY, RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, RAINBOW,
            GAY_MEN, LESBIAN, BISEXUAL, PANSEXUAL, TRANSGENDER, NON_BINARY,
            ASEXUAL, AROMANTIC, AROACE, DEMISEXUAL, GENDERFLUID,
            GENDERQUEER, AGENDER, BIGENDER, PANGENDER, OMNISEXUAL, POLYSEXUAL,
            INTERSEX, TWO_SPIRIT, SAPPIC, QUESTIONING, POLYAMOROUS, ABROSEXUAL,
            GRAYSEXUAL, GRAYROMANTIC, DEMIGENDER, DEMIBOY, DEMIGIRL, GENDERFLUX,
            BIGENDER_ALT, GENDERFAE, GENDERFAUN, XENOGENDER, LITHROMANTIC, FRAYSEXUAL,
            CUPIOSEXUAL, CUPIOROMANTIC, TRIGENDER, MULTIGENDER, POLYGENDER,
            ANDROGYNE, NEUTROIS, MAVERIQUE, OMNIGENDER, APORAGENDER, GENDERVOID,
            GREYGENDER, QUOIROMANTIC
        )
    }
}

object SparkleConfig {
    private const val PREFS_NAME = "SparklePrefs"
    private const val KEY_SPARKLE_COUNT = "sparkle_count"
    private const val KEY_MIN_SIZE = "min_size"
    private const val KEY_MAX_SIZE = "max_size"
    private const val KEY_MIN_LIFETIME = "min_lifetime"
    private const val KEY_MAX_LIFETIME = "max_lifetime"
    private const val KEY_WIGGLE_DISTANCE = "wiggle_distance"
    private const val KEY_MAX_ROTATION = "max_rotation"
    private const val KEY_FADE_IN = "fade_in"
    private const val KEY_FADE_OUT = "fade_out"
    private const val KEY_SELECTED_COLOR_PRESET = "selected_color_preset"
    private const val KEY_PAUSE_WHEN_SCREEN_OFF = "pause_when_screen_off"

    var sparkleCount = 5
    var selectedColorPreset = ColorPreset.WHITE

    var minSize = 4f
    var maxSize = 8f

    var minLifetime = 1500L
    var maxLifetime = 3000L

    var wiggleDistance = 4f
    var maxRotation = 10f

    var fadeInDuration = 500L
    var fadeOutDuration = 800L

    var pauseWhenScreenOff = true

    fun load(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sparkleCount = prefs.getInt(KEY_SPARKLE_COUNT, sparkleCount)
        minSize = prefs.getFloat(KEY_MIN_SIZE, minSize)
        maxSize = prefs.getFloat(KEY_MAX_SIZE, maxSize)
        minLifetime = prefs.getLong(KEY_MIN_LIFETIME, minLifetime)
        maxLifetime = prefs.getLong(KEY_MAX_LIFETIME, maxLifetime)
        wiggleDistance = prefs.getFloat(KEY_WIGGLE_DISTANCE, wiggleDistance)
        maxRotation = prefs.getFloat(KEY_MAX_ROTATION, maxRotation)
        fadeInDuration = prefs.getLong(KEY_FADE_IN, fadeInDuration)
        fadeOutDuration = prefs.getLong(KEY_FADE_OUT, fadeOutDuration)
        pauseWhenScreenOff = prefs.getBoolean(KEY_PAUSE_WHEN_SCREEN_OFF, pauseWhenScreenOff)
        
        val presetIndex = prefs.getInt(KEY_SELECTED_COLOR_PRESET, 1) // Default to WHITE (index 1)
        selectedColorPreset = ColorPreset.DEFAULT_PRESETS.getOrNull(presetIndex) ?: ColorPreset.WHITE
    }

    fun save(context: Context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().apply {
            putInt(KEY_SPARKLE_COUNT, sparkleCount)
            putFloat(KEY_MIN_SIZE, minSize)
            putFloat(KEY_MAX_SIZE, maxSize)
            putLong(KEY_MIN_LIFETIME, minLifetime)
            putLong(KEY_MAX_LIFETIME, maxLifetime)
            putFloat(KEY_WIGGLE_DISTANCE, wiggleDistance)
            putFloat(KEY_MAX_ROTATION, maxRotation)
            putLong(KEY_FADE_IN, fadeInDuration)
            putLong(KEY_FADE_OUT, fadeOutDuration)
            putInt(KEY_SELECTED_COLOR_PRESET, ColorPreset.DEFAULT_PRESETS.indexOf(selectedColorPreset))
            putBoolean(KEY_PAUSE_WHEN_SCREEN_OFF, pauseWhenScreenOff)
            apply()
        }
    }
}

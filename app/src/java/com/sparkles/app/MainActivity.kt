package com.sparkles.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    private lateinit var permissionButton: Button
    private lateinit var sparkleSwitch: SwitchMaterial
    private lateinit var applyButton: Button
    private lateinit var sparkleCountInput: EditText
    private lateinit var minSizeInput: EditText
    private lateinit var maxSizeInput: EditText
    private lateinit var minLifetimeInput: EditText
    private lateinit var maxLifetimeInput: EditText
    private lateinit var wiggleDistanceInput: EditText
    private lateinit var maxRotationInput: EditText
    private lateinit var fadeInInput: EditText
    private lateinit var fadeOutInput: EditText
    private lateinit var colorSelectorButton: Button
    private lateinit var colorSelectorContainer: android.widget.LinearLayout
    private lateinit var colorPresetList: android.widget.LinearLayout
    private var isServiceRunning = false

    companion object {
        private const val OVERLAY_PERMISSION_REQUEST_CODE = 1
        private const val PREFS_NAME = "SparklePrefs"
        private const val KEY_SERVICE_RUNNING = "service_running"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionButton = findViewById(R.id.permissionButton)
        sparkleSwitch = findViewById(R.id.sparkleSwitch)
        applyButton = findViewById(R.id.applyButton)
        sparkleCountInput = findViewById(R.id.sparkleCountInput)
        minSizeInput = findViewById(R.id.minSizeInput)
        maxSizeInput = findViewById(R.id.maxSizeInput)
        minLifetimeInput = findViewById(R.id.minLifetimeInput)
        maxLifetimeInput = findViewById(R.id.maxLifetimeInput)
        wiggleDistanceInput = findViewById(R.id.wiggleDistanceInput)
        maxRotationInput = findViewById(R.id.maxRotationInput)
        fadeInInput = findViewById(R.id.fadeInInput)
        fadeOutInput = findViewById(R.id.fadeOutInput)
        colorSelectorButton = findViewById(R.id.colorSelectorButton)
        colorSelectorContainer = findViewById(R.id.colorSelectorContainer)
        colorPresetList = findViewById(R.id.colorPresetList)

        setupPermissionButton()
        setupSparkleSwitch()
        setupApplyButton()
        setupColorSelector()

        loadSavedParameters()
        checkPermissionStatus()
        checkServiceState()
    }

    override fun onResume() {
        super.onResume()
        checkPermissionStatus()
        checkServiceState()
    }

    private fun setupPermissionButton() {
        permissionButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun setupSparkleSwitch() {
        sparkleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (hasOverlayPermission()) {
                    startSparkleService()
                } else {
                    sparkleSwitch.isChecked = false
                    Toast.makeText(
                        this,
                        "Please grant overlay permission first",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                stopSparkleService()
            }
        }
    }

    private fun checkPermissionStatus() {
        val hasPermission = hasOverlayPermission()
        if (hasPermission) {
            permissionButton.text = "Granted ✓"
            permissionButton.isEnabled = false
            sparkleSwitch.isEnabled = true
        } else {
            permissionButton.text = "Grant permission"
            permissionButton.isEnabled = true
            sparkleSwitch.isEnabled = false
            if (sparkleSwitch.isChecked) {
                sparkleSwitch.isChecked = false
            }
        }
    }

    private fun hasOverlayPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(this)
        } else {
            true
        }
    }

    private fun startSparkleService() {
        SparkleService.startService(this)
        isServiceRunning = true
        saveServiceState(true)
    }

    private fun stopSparkleService() {
        SparkleService.stopService(this)
        isServiceRunning = false
        saveServiceState(false)
    }

    private fun setupApplyButton() {
        applyButton.setOnClickListener {
        applyParameters()
        }
    }

    private fun applyParameters() {
        try {
            SparkleConfig.sparkleCount = sparkleCountInput.text.toString().toInt()
            SparkleConfig.minSize = minSizeInput.text.toString().toFloat()
            SparkleConfig.maxSize = maxSizeInput.text.toString().toFloat()
            SparkleConfig.minLifetime = minLifetimeInput.text.toString().toLong()
            SparkleConfig.maxLifetime = maxLifetimeInput.text.toString().toLong()
            SparkleConfig.wiggleDistance = wiggleDistanceInput.text.toString().toFloat()
            SparkleConfig.maxRotation = maxRotationInput.text.toString().toFloat()
            SparkleConfig.fadeInDuration = fadeInInput.text.toString().toLong()
            SparkleConfig.fadeOutDuration = fadeOutInput.text.toString().toLong()

            saveParameters()

            if (isServiceRunning) {
                stopSparkleService()
                startSparkleService()
            }

            Toast.makeText(this, "Parameters applied", Toast.LENGTH_SHORT).show()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid input values", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupColorSelector() {
        colorSelectorButton.setOnClickListener {
            val isVisible = colorSelectorContainer.visibility == android.view.View.VISIBLE
            colorSelectorContainer.visibility = if (isVisible) android.view.View.GONE else android.view.View.VISIBLE
            if (!isVisible) {
                populateColorPresets()
            }
        }
    }

    private fun populateColorPresets() {
        colorPresetList.removeAllViews()
        
        for (preset in ColorPreset.DEFAULT_PRESETS) {
            val presetView = createColorPresetView(preset)
            colorPresetList.addView(presetView)
        }
    }

    private fun createColorPresetView(preset: ColorPreset): android.view.View {
        val context = this
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val containerWidth = (screenWidth * 0.5).toInt()
        
        val layoutParams = android.widget.LinearLayout.LayoutParams(
            containerWidth,
            android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 0, 8)
        
        val container = android.widget.LinearLayout(context).apply {
            orientation = android.widget.LinearLayout.HORIZONTAL
            setLayoutParams(layoutParams)
            setPadding(8, 8, 8, 8)
        }
        container.gravity = android.view.Gravity.CENTER
        
        val colorPreview = android.widget.LinearLayout(context).apply {
            orientation = android.widget.LinearLayout.HORIZONTAL
        }
        
        val height = (48 * displayMetrics.density).toInt()
        colorPreview.layoutParams = android.widget.LinearLayout.LayoutParams(
            android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
            height
        )
        
        if (preset.isRainbow) {
            // Show horizontal stripes for rainbow/flag presets
            val stripeWidth = 45
            for (color in preset.rainbowColors) {
                val stripe = android.view.View(context)
                stripe.layoutParams = android.widget.LinearLayout.LayoutParams(stripeWidth, height)
                stripe.setBackgroundColor(color)
                colorPreview.addView(stripe)
            }
        } else if (preset.useTextDisplay) {
            val labelBackground = when (preset.name) {
                "Black" -> android.graphics.Color.WHITE
                "White" -> android.graphics.Color.BLACK
                else -> preset.color
            }
            val labelColor = when (preset.name) {
                "Black" -> android.graphics.Color.BLACK
                "White" -> android.graphics.Color.WHITE
                else -> android.graphics.Color.DKGRAY
            }
            colorPreview.layoutParams = android.widget.LinearLayout.LayoutParams(
                (120 * displayMetrics.density).toInt(),
                height
            )
            colorPreview.setBackgroundColor(labelBackground)
            colorPreview.gravity = android.view.Gravity.CENTER
            
            val label = android.widget.TextView(context)
            label.layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            )
            label.text = preset.name
            label.textSize = 20f
            label.setTextColor(labelColor)
            colorPreview.addView(label)
        } else {
            // Single color block
            colorPreview.layoutParams = android.widget.LinearLayout.LayoutParams(48, 48)
            colorPreview.setBackgroundColor(preset.color)
        }
        
        container.addView(colorPreview)
        
        container.setOnClickListener {
            SparkleConfig.selectedColorPreset = preset
            updateColorSelectorButton()
            colorSelectorContainer.visibility = android.view.View.GONE
            saveParameters()
            
            if (isServiceRunning) {
                stopSparkleService()
                startSparkleService()
            }
        }
        
        return container
    }

    private fun updateColorSelectorButton() {
        val preset = SparkleConfig.selectedColorPreset

        colorSelectorButton.backgroundTintList = null
        colorSelectorButton.backgroundTintMode = null
        
        if (preset.isRainbow) {
            // Show color preview for rainbow/flag presets
            val height = 48
            val stripeWidth = 12
            val totalWidth = preset.rainbowColors.size * stripeWidth
            
            val bitmap = android.graphics.Bitmap.createBitmap(totalWidth, height, android.graphics.Bitmap.Config.ARGB_8888)
            val canvas = android.graphics.Canvas(bitmap)
            val paint = android.graphics.Paint()
            
            for (i in preset.rainbowColors.indices) {
                paint.color = preset.rainbowColors[i]
                canvas.drawRect((i * stripeWidth).toFloat(), 0f, ((i + 1) * stripeWidth).toFloat(), height.toFloat(), paint)
            }
            
            val drawable = android.graphics.drawable.BitmapDrawable(resources, bitmap)
            colorSelectorButton.background = drawable
            colorSelectorButton.text = ""
        } else if (preset.useTextDisplay) {
            colorSelectorButton.background = null
            colorSelectorButton.setBackgroundColor(
                when (preset.name) {
                    "Black" -> android.graphics.Color.WHITE
                    "White" -> android.graphics.Color.BLACK
                    else -> preset.color
                }
            )
            colorSelectorButton.setTextColor(
                when (preset.name) {
                    "Black" -> android.graphics.Color.BLACK
                    "White" -> android.graphics.Color.WHITE
                    else -> android.graphics.Color.DKGRAY
                }
            )
            colorSelectorButton.text = preset.name
        } else {
            // Show single color block
            colorSelectorButton.background = null
            colorSelectorButton.setBackgroundColor(preset.color)
            colorSelectorButton.text = ""
        }
    }

    private fun saveParameters() {
        SparkleConfig.save(this)
    }

    private fun loadSavedParameters() {
        SparkleConfig.load(this)
        sparkleCountInput.setText(SparkleConfig.sparkleCount.toString())
        minSizeInput.setText(SparkleConfig.minSize.toString())
        maxSizeInput.setText(SparkleConfig.maxSize.toString())
        minLifetimeInput.setText(SparkleConfig.minLifetime.toString())
        maxLifetimeInput.setText(SparkleConfig.maxLifetime.toString())
        wiggleDistanceInput.setText(SparkleConfig.wiggleDistance.toString())
        maxRotationInput.setText(SparkleConfig.maxRotation.toString())
        fadeInInput.setText(SparkleConfig.fadeInDuration.toString())
        fadeOutInput.setText(SparkleConfig.fadeOutDuration.toString())
        updateColorSelectorButton()
    }

    private fun saveServiceState(isRunning: Boolean) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_SERVICE_RUNNING, isRunning).apply()
    }

    private fun checkServiceState() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val wasRunning = prefs.getBoolean(KEY_SERVICE_RUNNING, false)
        
        if (wasRunning && hasOverlayPermission()) {
            sparkleSwitch.isChecked = true
            isServiceRunning = true
        } else {
            sparkleSwitch.isChecked = false
            isServiceRunning = false
            saveServiceState(false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            checkPermissionStatus()
        }
    }
}

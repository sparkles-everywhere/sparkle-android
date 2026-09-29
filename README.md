# Sparkles

A lightweight Android app that displays animated sparkles as a system-wide transparent overlay. The sparkles appear over other apps without intercepting touch input, creating a subtle magical effect on your device.

This is the Android counterpart to the Linux/X11 `desktop-sparkles.py` implementation.

## Features

- **System-wide overlay**: Sparkles appear over all apps
- **Touch-through**: Overlay doesn't intercept touch events - you can interact normally with apps underneath
- **Lightweight rendering**: Uses Android Canvas with manually drawn paths (no text/glyph rendering)
- **Foreground service**: Effect continues running when the app UI is closed
- **Five sparkle types**: Diamond, hollow diamond, soft star, six-point, and eight-point shapes
- **Smooth animations**: Fade in/out, gentle wiggle, subtle rotation, and pulsing
- **Configurable settings**: Adjust sparkle count, size, lifetime, animation speed, and more

## Screenshots

Coming soon...

## Requirements

- Android 7.0 (API level 24) or higher
- "Display over other apps" permission (SYSTEM_ALERT_WINDOW)

## Building

### Prerequisites

- Android Studio or command-line Android SDK
- JDK 17
- Gradle 8.2.0 (included via Gradle wrapper)

### Build from source

```bash
# Clone the repository
git clone https://github.com/sparkles-everywhere/sparkle-android
cd sparkle-android

# Build the APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

## Usage

1. Open the Sparkles app
2. Grant the "Display over other apps" permission when prompted (or tap the permission button)
3. Toggle the sparkles ON
4. The sparkles will appear over your screen
5. Customize sparkle behavior (count, size, animation speed, etc.)
6. You can close the app - the effect will continue running
7. Toggle OFF to stop the effect

The overlay is completely transparent to touch input - you can tap buttons, type, and use your phone normally while sparkles are visible.

## Architecture

### Core Components

- **MainActivity**: Simple UI with ON/OFF switch, permission status, and settings access
- **SparkleService**: Foreground service that manages the overlay window and animation loop
- **SparkleView**: Custom View that renders all sparkles onto a single Canvas
- **Sparkle**: Data class representing individual sparkle properties (position, size, lifetime, type, etc.)
- **SparkleRenderer**: Handles drawing logic for the five sparkle types using Path primitives

### Technical Details

- **Rendering**: Single Canvas with Path-based drawing (no per-sparkle Views, no text rendering)
- **Animation**: Choreographer-based frame callback targeting 30-60 FPS
- **Performance**: Optimized for low CPU usage (~3% target on desktop, similar goal on Android)
- **Touch handling**: Uses `FLAG_NOT_FOCUSABLE` and `FLAG_NOT_TOUCHABLE` window flags
- **Persistence**: ON/OFF state is persisted across app restarts

## Configuration

All sparkle settings are configurable via the in-app settings:

- **Sparkle count**: Number of sparkles on screen (default: 45)
- **Size range**: Minimum and maximum sparkle size (default: 3-7)
- **Lifetime range**: How long sparkles live before respawning (default: 1.5-4.0 seconds)
- **Wiggle distance**: How far sparkles drift from their base position (default: 4)
- **Rotation amount**: Maximum rotation angle (default: 10 degrees)
- **Animation speed**: Overall speed of sparkle animations

Settings are persisted across app restarts.

## Performance

The desktop Cairo-based implementation consumes approximately 3% CPU. The Android version follows the same design principles:

- Single drawing surface
- No per-frame object allocation
- Reused Paint and Path objects
- Efficient animation loop using Choreographer
- Graceful degradation under load

## Contributing

Contributions are welcome!

## Notes

Amazing what I can do in the throes of madness (hypomania).

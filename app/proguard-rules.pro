# Keep all classes and their generic type information
-keepattributes Signature
-keepattributes *Annotation*
-keep class * { *; }

# Keep all interfaces
-keep interface * { *; }

# Prevent warnings for missing classes
-dontwarn **

# Additional rules for libraries (already included in your file)
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keep class dagger.hilt.** { *; }
-keep class kotlinx.coroutines.** { *; }
-keep class okhttp3.logging.** { *; }
-keep class coil.** { *; }
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Strip all Timber log calls except Timber.e, Timber.w, and Timber.wtf
-assumenosideeffects class timber.log.Timber$Forest {
    public *** d(...);
    public *** v(...);
    public *** i(...);
    public *** log(...);
    public *** tag(...);
}


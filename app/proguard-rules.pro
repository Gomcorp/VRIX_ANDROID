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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontshrink

-dontwarn android.support.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**

-keep interface com.gomcorp.vrix.util.KeepName
-keepnames @com.gomcorp.vrix.util.KeepName class *
-keepclassmembernames class * {
    @com.gomcorp.vrix.util.KeepName *;
}

### OKHTTP

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote okhttp3.internal.Platform


##############################################################################################
###################################### Retofit ###############################################
-dontwarn retrofit2.**
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
##############################################################################################

#############################################################################################
###################################### simpleframework.xml ###################################
-dontwarn org.simpleframework.xml.stream.**
-keep public class org.simpleframework.** { *; }
-keep class org.simpleframework.xml.** { *; }
-keep class org.simpleframework.xml.core.** { *; }
-keep class org.simpleframework.xml.util.** { *; }

-keepattributes ElementList, Root, *Annotation*

-keepclassmembers class * {
    @org.simpleframework.xml.* *;
}
#############################################################################################


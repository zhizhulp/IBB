# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\张凯雅\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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
-dontoptimize
# NoHttp
-keepclassmembers class ** {
    private javax.net.ssl.SSLSocketFactory delegate;
}
# adapter
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}
# umeng
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep public class com.umeng.socialize.* {*;}
-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }
-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature
-keep class * extends com.umeng.socialize.net.base.SocializeReseponse {
   *;
}
# gson
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
 # eventbus混淆
 -keepattributes *Annotation*
 -keepclassmembers class * {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }
 # okhttp混淆
 -dontwarn okhttp3.**
 -dontwarn okio.**
 -dontwarn javax.annotation.**
 -dontwarn org.conscrypt.**
 # adapter混淆
 -keep class com.chad.library.adapter.** {
 *;
 }
 -keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
 -keep public class * extends com.chad.library.adapter.base.BaseViewHolder
 -keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
      <init>(...);
 }

 -keepattributes *JavascriptInterface*
 -optimizationpasses 5          # 指定代码的压缩级别
 -verbose
 -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

 -keep public class * extends android.app.AppCompatActivity      # 保持哪些类不被混淆
 -keep public class * extends android.app.Application   # 保持哪些类不被混淆
 -keep public class * extends android.app.Service       # 保持哪些类不被混淆
 -keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
 -keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
 -keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
 -keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
 -keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆
 -keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
     native <methods>;
 }
 -keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
     public <init>(android.content.Context, android.util.AttributeSet);
 }
 -keepclasseswithmembers class * {# 保持自定义控件类不被混淆
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }
 -keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
     public void *(android.view.View);
 }
 -keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }
 -keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
     public static final android.os.Parcelable$Creator *;
 }
 #如果有引用v4包可以添加下面这行
 -keep public class * extends android.support.v4.app.Fragment
 #忽略警告
 -ignorewarning
 #实体类不混淆
 -keep class com.example.ibb.entity.**{
     *;
 }
 #adapter不混淆
 -keep class com.example.ibb.adapter.**{
     *;
 }

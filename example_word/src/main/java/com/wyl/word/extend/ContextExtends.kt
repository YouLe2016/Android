package com.wyl.word.extend

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.Gravity
import android.widget.Toast


//----------app版本相关----------
fun Context.versionCode(): Int {
    return packageManager.getPackageInfo(packageName, 0).versionCode
}

fun Context.versionName(): String {
    return packageManager.getPackageInfo(packageName, 0).versionName
}


//----------copy----------
fun Context.copyToClipboard(content: String) {
    //获取剪贴版
//    val clipboard = getSystemService(this, ClipboardManager::class.java)
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    //创建ClipData对象
    //第一个参数只是一个标记，随便传入。
    //第二个参数是要复制到剪贴版的内容
    val clip = ClipData.newPlainText("simple text", content)
    //传入clipdata对象.
    clipboard.primaryClip = clip
}

//----------toast----------
//----------toast----------
fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.applicationContext, text, duration).show()
}

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.applicationContext, resId, duration).show()
}

fun Context.centerToast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    val t = Toast.makeText(this.applicationContext, resId, duration)
    t.setGravity(Gravity.CENTER, 0, 0)
    t.show()
}

//----------尺寸转换----------

fun Context.dp2px(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.px2dp(pxValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun Context.sp2px(spValue: Float): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (spValue * scale + 0.5f).toInt()
}

fun Context.px2sp(pxValue: Float): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (pxValue / scale + 0.5f).toInt()
}

//----------屏幕尺寸----------
fun Context.getScreenWidth(): Int {
//    var wm: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            ?: return resources.displayMetrics.widthPixels
//    var point = Point()
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//        wm.defaultDisplay.getRealSize(point)
//    } else {
//        wm.defaultDisplay.getSize(point)
//    }
//    return point.x
    return resources.displayMetrics.widthPixels
}

fun Context.getScreenHeight(): Int {
//    var wm: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        ?: return resources.displayMetrics.heightPixels
//    var point = Point()
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//        wm.defaultDisplay.getRealSize(point)
//    } else {
//        wm.defaultDisplay.getSize(point)
//    }
//    return point.y
    return resources.displayMetrics.heightPixels
}


//----------NetWork----------
/**
 * 打开网络设置
 */
fun Context.openWirelessSettings() {
    startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

/**
 * 网络是否连接
 */
fun Context.isConnected(): Boolean {
    var info = this.getActiveNetworkInfo()
    return info.isConnected
}

/**
 * 判断网络是否是移动数据
 */
fun Context.isMobileData(): Boolean {
    val info = this.getActiveNetworkInfo()
    return (info.isAvailable && info.type == ConnectivityManager.TYPE_MOBILE)
}

/**
 * 退回到桌面
 */
fun Context.startHomeActivity() {
    val homeIntent = Intent(Intent.ACTION_MAIN)
    homeIntent.addCategory(Intent.CATEGORY_HOME)
    startActivity(homeIntent)
}


@SuppressLint("MissingPermission")
private fun Context.getActiveNetworkInfo(): NetworkInfo {
    val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return manager.activeNetworkInfo
}



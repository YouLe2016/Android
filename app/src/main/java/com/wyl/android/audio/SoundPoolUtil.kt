package com.wyl.android.audio

import android.content.Context
import android.media.SoundPool
import androidx.annotation.RawRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-10-24 12:25
 * 内容描述：
 * 修改说明：
 */

object SoundPoolUtil : LifecycleObserver {
    var soundPool: SoundPool? = null

    private val loadSource by lazy { hashMapOf<Int, Int>() }

    fun init(maxStreams: Int = 1) {
//    val attr = AudioAttributes.Builder()
//        .setUsage(AudioAttributes.USAGE_MEDIA)
//        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//        .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(maxStreams)  //设置允许同时播放的流的最大值
//        .setAudioAttributes(attr)   //完全可以设置为null
            .build()
    }

    fun loadSound(
        context: Context,
        @RawRes vararg resIdRes: Int,
        onLoadComplete: (() -> Unit)? = null
    ) {
        if (soundPool == null) {
            init()
        }
        soundPool?.apply {
            setOnLoadCompleteListener { _, sampleId, status ->
                if (status == 0) onLoadComplete?.invoke()
            }
            resIdRes.forEach {
                val loadId = load(context, it, 1)
                loadSource[it] = loadId
            }
        }
    }

    /**
     * 播放音频文件
     *
     * 同时播放两个音频文件, 可能只播放其中一个.
     *
     * 建议先[loadSound]后[playSound]
     *
     */
    fun playSound(context: Context, @RawRes resIdRes: Int) {
        if (loadSource.containsKey(resIdRes)) {
            soundPool?.play(loadSource[resIdRes]!!, 1f, 1f, 1, 0, 1f)
        } else {
            loadSound(context, resIdRes) {
                // 短时间可能只回调一次
                playSound(context, resIdRes)
            }
        }
    }

    fun stopSound(@RawRes resIdRes: Int) {
        if (loadSource.containsKey(resIdRes)) {
            val remove = loadSource.remove(resIdRes)!!
            soundPool?.stop(remove)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun autoResume() {
        soundPool?.autoResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun autoPause() {
        soundPool?.autoPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun release() {
        soundPool?.release()
        soundPool = null
        loadSource.clear()
    }

}
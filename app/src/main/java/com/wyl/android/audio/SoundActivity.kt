package com.wyl.android.audio

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wyl.android.R
import kotlinx.android.synthetic.main.activity_sound.*


class SoundActivity : AppCompatActivity() {

    private val soundRes = intArrayOf(
        R.raw.audio01,
        R.raw.audio02,
        R.raw.audio03,
        R.raw.audio04,
        R.raw.audio05,
        R.raw.audio06,
        R.raw.audio07,
        R.raw.audio08,
        R.raw.audio09,
        R.raw.audio10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        lifecycle.addObserver(SoundPoolUtil)
        SoundPoolUtil.init(3)

        SoundPoolUtil.playSound(this, soundRes.last())
        soundRes.forEach {
            SoundPoolUtil.loadSound(this, it)
        }


        btSoundPool.setOnClickListener {
            SoundPoolUtil.playSound(it.context, soundRes[0])
        }
        button7.setOnClickListener {
            SoundPoolUtil.playSound(it.context, soundRes[6])
        }
        button8.setOnClickListener {
            SoundPoolUtil.playSound(it.context, soundRes[7])
        }
        button9.setOnClickListener {
            SoundPoolUtil.playSound(it.context, soundRes[8])
        }
        button10.setOnClickListener {
            SoundPoolUtil.playSound(it.context, soundRes[9])
        }

        val player = MediaPlayer.create(this, R.raw.audio01)

        btMediaplayer.setOnClickListener {
            player.start()
            player.setOnCompletionListener {
                player.reset()
                val afd = resources.openRawResourceFd(R.raw.audio02)
                player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd!!.close()
                player.prepare()
                player.start()
                player.setOnCompletionListener(null)
            }
        }
    }
}



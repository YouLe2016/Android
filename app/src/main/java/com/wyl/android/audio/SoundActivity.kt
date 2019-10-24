package com.wyl.android.audio

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wyl.android.R
import kotlinx.android.synthetic.main.activity_sound.*


class SoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        lifecycle.addObserver(SoundPoolUtil)

        SoundPoolUtil.loadSound(this, R.raw.ddsy, R.raw.djssy) {
            //            SoundPoolUtil.playSound(this, R.raw.djssy)
        }

        btSoundPool.setOnClickListener {
            SoundPoolUtil.playSound(it.context, R.raw.ddsy)
        }


        val player = MediaPlayer.create(this, R.raw.ddsy)

        btMediaplayer.setOnClickListener {
            player.start()
            player.setOnCompletionListener {
                player.reset()
                val afd = resources.openRawResourceFd(R.raw.djssy)
                player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd!!.close()
                player.prepare()
                player.start()
                player.setOnCompletionListener(null)
            }
        }
    }
}



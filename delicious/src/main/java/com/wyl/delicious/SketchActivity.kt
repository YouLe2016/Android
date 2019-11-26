package com.wyl.delicious

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sketch.*

class SketchActivity : AppCompatActivity() {

    private val url = "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sketch)


        image.apply {
            //            isShowImageFromEnabled = true
//            scaleType = ImageView.ScaleType.CENTER_CROP
            displayImage(url)
        }

        // Use Sketch.display()
//        Sketch.with(context).display(netImageUri, sketchImageView).commit();

//        Sketch.with(this).display(url,image).commit()
    }
}

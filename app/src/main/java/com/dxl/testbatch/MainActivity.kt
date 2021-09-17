package com.dxl.testbatch

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dxl.testbatch.util.BSPatchUtil
import java.io.File
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {

            //当前apk路径，实际开发中用这个，当前没有使用
            val sourceDir =
                packageManager.getApplicationInfo(BuildConfig.APPLICATION_ID, 0).sourceDir

            val file = File(sourceDir)



            thread {
                val old = getExternalFilesDir("apk")!!.absolutePath + "/239.apk"
                val new = getExternalFilesDir("apk")!!.absolutePath + "/new.apk"
                val patch = getExternalFilesDir("apk")!!.absolutePath + "/1.patch"
                val time = measureTimeMillis {
                    BSPatchUtil.bspatch(old, new, patch)
                }
                runOnUiThread {
                    Toast.makeText(this, "打包成功，用时$time~", Toast.LENGTH_SHORT).show()
                }

            }
            return true
        }

        return super.onTouchEvent(event)
    }
}
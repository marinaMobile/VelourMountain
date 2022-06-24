package com.hecorat.screenrecorder.fre

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_game.*

class GameAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        Dialog().show(supportFragmentManager, "MyCustomDialog")

        llTop.setOnDragListener(dragListener)
        llBottom.setOnDragListener(dragListener)


        dragView.setOnLongClickListener{
            val clipText = "ClipData text"
            val item = ClipData.Item(clipText)
            val mimeTypes =  arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadBuilder = View.DragShadowBuilder(it)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(data, dragShadBuilder, it, 0)
            }
            it.visibility = View.INVISIBLE
            true
        }


    }
    val dragListener= View.OnDragListener {view, dragEvent ->
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN )
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP  -> {
                view.invalidate()
                val v = dragEvent.localState as View
                val owner = v.parent as ViewGroup
                owner.removeView(v)
                val destination = view as RelativeLayout
                destination.addView(v)
                v.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                startActivity(Intent(this, WinnerAct::class.java))
                dragView.visibility = View.INVISIBLE
                finish()
                true
            }
            else -> false
        }
    }
}
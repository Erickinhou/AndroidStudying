package br.tutorial.kidsdrawingapp

import android.Manifest
import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var drawingView: DrawingView? = null
    private var linearLayoutPaintColors: LinearLayout? = null
    private var selectedButtonView: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20F)

        linearLayoutPaintColors = findViewById(R.id.ll_paint_colors)
        setLinearLayoutClick()


        val ibBrush: ImageButton = findViewById(R.id.ib_brush)
        ibBrush.setOnClickListener {
            showBrushSizeChooserDialog()
        }

        val ibGallery: ImageButton = findViewById(R.id.ib_gallery)
        ibGallery.setOnClickListener {   }
    }

    private fun requestExternalStorePermission (){
        if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission() // Do the request!!
                ) { isGranted: Boolean ->
                    if (isGranted) {
                        // Permission is granted. Continue the action or workflow in your
                        // app.
                    } else {
                        // Explain to the user that the feature is unavailable because the
                        // feature requires a permission that the user has denied. At the
                        // same time, respect the user's decision. Don't link to system
                        // settings in an effort to convince the user to change their
                        // decision.
                    }
                }
        } else{
            Snackbar.make(this as View, "Request camera denied",Snackbar.LENGTH_LONG ).show()
        }

    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")
        val smallBtn = brushDialog.findViewById<ImageButton>(R.id.ib_small_brush)
        smallBtn.setOnClickListener {
            drawingView?.setSizeForBrush(10F)
            brushDialog.dismiss()
        }
        val mediumBtn = brushDialog.findViewById<ImageButton>(R.id.ib_medium_brush)
        mediumBtn.setOnClickListener {
            drawingView?.setSizeForBrush(20F)
            brushDialog.dismiss()
        }
        val largeBtn = brushDialog.findViewById<ImageButton>(R.id.ib_large_brush)
        largeBtn.setOnClickListener {
            drawingView?.setSizeForBrush(30F)
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

    private fun setLinearLayoutClick() {
        linearLayoutPaintColors?.let { linearlayout ->
            for (imageButton in linearlayout) {
                val mImageButtonCurrentPaint = imageButton as ImageButton
                val parsedColor = Color.parseColor(mImageButtonCurrentPaint.tag.toString())
                mImageButtonCurrentPaint.setOnClickListener { view ->
                    handleButtonClick(view as ImageButton, parsedColor)
                }
            }
        }
    }

    private fun handleButtonClick(imageButton: ImageButton, parsedColor: Int) {
        if (selectedButtonView?.tag != imageButton.tag) {
            drawingView?.setColorForBrush(parsedColor)
            drawDefaultPallet(selectedButtonView)
            drawPressedPallet(imageButton)
            selectedButtonView = imageButton
        }
    }

    private fun drawDefaultPallet(palletView: ImageButton?) {
        palletView?.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.pallet_normal
            )
        )
    }

    private fun drawPressedPallet(palletView: ImageButton?) {
        palletView?.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.pallet_pressed
            )
        )
    }

}
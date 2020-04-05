package com.example.criminalapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_missing.*
//import com.android.volley.Response
//import com.android.volley.*
//import com.android.volley.toolbox.Volley
//import com.google.android.gms.common.api.Response
//import java.io.IOException
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import java.io.IOException

class MissingActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var imageData: ByteArray? = null
    private val postURL: String = "http://10.0.2.2:5000" // remember to use your own api


    companion object {
        private const val IMAGE_PICK_CODE = 999
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missing)

        imageView = findViewById(R.id.imageViewer)

        uploadBtn.setOnClickListener {
            launchGallery()
        }


        checkimageBtn.setOnClickListener {
            //uploadImage()
            val intent=Intent(this, PinpointedActivity::class.java)
            startActivity(intent)
        }
    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun uploadImage() {
        imageData?: return
        val request = object : VolleyFileUploadRequest(
            Method.POST,
            postURL,
            Response.Listener {
                println("response is: $it")
            },
            Response.ErrorListener {
                println("error is: $it")
            }
        ) {
            override fun getByteData(): MutableMap<String, FileDataPart> {
                var params = HashMap<String, FileDataPart>()
                params["imageFile"] = FileDataPart("image", imageData!!, "jpeg")
                return params
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    @Throws(IOException::class)
    private fun createImageData(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            if (uri != null) {
                imageView.setImageURI(uri)
                createImageData(uri)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}

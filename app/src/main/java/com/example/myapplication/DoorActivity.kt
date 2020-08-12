package com.example.myapplication

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.lifecycleScope
import coil.Coil
import coil.request.GetRequest
import com.example.myapplication.ui.EnergyDayfragment
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DoorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){

       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_door)
        val photo = intent.getStringExtra("photo")
        val dooropen = findViewById<Button>(R.id.button3)
        val photoview = findViewById<ImageView>(R.id.imageView)
        dooropen.setOnClickListener{
           doorOpen()
        }

        lifecycleScope.launch {
            val bitmap = getBitmap(photo ?: return@launch)
            photoview.setImageBitmap(bitmap)
        }
    }

    suspend fun getBitmap(url: String): Bitmap? {
        val request = GetRequest.Builder(this)
            .data(url)
            .build()
        val result = Coil.imageLoader(this).execute(request).drawable
        return result?.toBitmap(result.intrinsicWidth, result.intrinsicHeight)
    }

    fun doorOpen(){
        lifecycleScope.launch {
            try {
                WebClient.setDoorOpen()
                Toast.makeText(this@DoorActivity, "Открытие двери", Toast.LENGTH_SHORT)
                    .show()
            }catch(ex: HttpException){
                Toast.makeText(this@DoorActivity, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
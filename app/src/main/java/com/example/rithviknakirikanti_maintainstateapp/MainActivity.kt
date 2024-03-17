package com.example.rithviknakirikanti_maintainstateapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    //global variables
    private lateinit var imageView:ImageView
    private lateinit var editText:EditText
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var button:Button

    private val animalImages = listOf(R.drawable.cat, R.drawable.lion, R.drawable.panda)
    private var pickRandomImageInt:Int = animalImages[Random.nextInt(0, animalImages.size)]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize the imageView, editText, button, and sharedPreferences
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        //reload image
        val savedImageId = sharedPreferences.getInt("imageID", R.drawable.cat)
        imageView.setImageResource(savedImageId)

        //reload editText
        editText.setText(sharedPreferences.getString("editTextContent", ""))

        button.setOnClickListener {
            pickRandomImageInt = animalImages[Random.nextInt(0, animalImages.size)]
            imageView.setImageResource(pickRandomImageInt)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val editor = sharedPreferences.edit()
        editor.putInt("imageID", pickRandomImageInt)
        editor.putString("editTextContent", editText.text.toString())
        editor.apply()
    }
}
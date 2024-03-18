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
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var button: Button

    private val animalImages = listOf(R.drawable.cat, R.drawable.lion, R.drawable.panda)
    private var pickRandomImageInt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the imageView, editText, button, and sharedPreferences
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        //Load last saved image or random image
        pickRandomImageInt = sharedPreferences.getInt("imageID", getRandomImage())
        imageView.setImageResource(pickRandomImageInt)

        //Reload editText content
        editText.setText(sharedPreferences.getString("editTextContent", ""))

        //on click listener for getting animals
        button.setOnClickListener {
            pickRandomImageInt = getRandomImage()
            imageView.setImageResource(pickRandomImageInt)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //save the current state
        val editor = sharedPreferences.edit()
        editor.putInt("imageID", pickRandomImageInt)
        editor.putString("editTextContent", editText.text.toString())
        editor.apply()
    }

    private fun getRandomImage(): Int {
        return animalImages[Random.nextInt(animalImages.size)]
    }
}

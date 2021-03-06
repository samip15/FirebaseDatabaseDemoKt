package com.sam.firebasedemokt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class MainActivity : AppCompatActivity() {
    lateinit var mEditTextName: EditText
    lateinit var ratingBar: RatingBar
    lateinit var buttonSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mEditTextName = findViewById(R.id.editTextName)
        ratingBar = findViewById(R.id.ratingBar)
        buttonSave = findViewById(R.id.save)
        buttonSave.setOnClickListener {
            saveHero()
        }
    }

    private fun saveHero() {
        val name = mEditTextName.text.toString().trim()
        if (name.isEmpty()) {
            mEditTextName.error = "Please enter a name"
            return
        }
        val ref = FirebaseDatabase
            .getInstance("https://fir-demokt-default-rtdb.firebaseio.com/")
            .getReference("heroes")
        val heroId = ref.push().key
        val hero = Hero(heroId!!, name, ratingBar.numStars)
        ref.child(heroId).setValue(hero).addOnCompleteListener {
            Toast.makeText(this, "Hero Saved Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}
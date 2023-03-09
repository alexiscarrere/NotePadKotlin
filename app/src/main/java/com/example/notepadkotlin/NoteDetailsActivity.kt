package com.example.notepadkotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class NoteDetailsActivity : AppCompatActivity() {
    companion object {
        val EXTRA_NOTE = "note"
        val EXTRA_NOTE_INDEX = "noteIndex"
    }

    lateinit var note : Note

    var noteIndex : Int = -1

    lateinit var titleView : TextView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)



        note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            Log.i("Mon probleme", "je suis dans tiramisu")
             intent.getParcelableExtra(EXTRA_NOTE, Note::class.java)!!
        } else {
            Log.i("Mon probleme", "je suis  déprécié")
            intent.getParcelableExtra<Note>(EXTRA_NOTE)!!
        }

        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)
        titleView = findViewById<TextView>(R.id.title) as TextView
        textView = findViewById<TextView>(R.id.text)

        titleView.text = note.title
        textView.text = note.text

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)   {
            R.id.action_save -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
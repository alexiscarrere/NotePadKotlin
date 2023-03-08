package com.example.notepadkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes:MutableList<Note>
    lateinit var adapter: NoteAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = mutableListOf<Note>()
        notes.add(Note("Note 1 ", "Alexis doit nourrir le chien"))
        notes.add(Note("Note 2 ", "Naca a pull github"))
        notes.add(Note("Bastien tu rêves ", "Prosternez vous devant Bastien le meilleur codeur"))
        notes.add(Note("Yes", "Voir Stephou"))

        adapter = NoteAdapter(notes, this)

        val recyclerView = findViewById(R.id.notes_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onClick(view: View) {
        if (view.tag != null) {
            Log.i("NoteActivity", "click sur une note")
        }
    }
}
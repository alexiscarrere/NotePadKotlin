package com.example.notepadkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes:MutableList<Note>
    lateinit var adapter: NoteAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK || data == null){
            return
        }
        when(requestCode){
            NoteDetailsActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailsActivity.EXTRA_NOTE_INDEX, -1)
        val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            Log.i("Mon probleme", "je suis dans tiramisu")
            data.getParcelableExtra(NoteDetailsActivity.EXTRA_NOTE, Note::class.java)!!
        } else {
            Log.i("Mon probleme", "je suis  déprécié")
            data.getParcelableExtra<Note>(NoteDetailsActivity.EXTRA_NOTE)!!
        }
        saveNote(note, noteIndex)
    }


    override fun onClick(view: View) {
        if (view.tag != null) {
            //Log.i("NoteActivity", "click sur une note")
            showNoteDetail(view.tag as Int)
        }
    }

    fun saveNote(note: Note, noteIndex: Int){
        notes[noteIndex] = note
        adapter.notifyDataSetChanged()
    }

    fun showNoteDetail(noteIndex : Int) {
        val note = notes[noteIndex]
        val intent = Intent(this, NoteDetailsActivity::class.java)

        intent.putExtra(NoteDetailsActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailsActivity.EXTRA_NOTE_INDEX, noteIndex)
        //startActivity(intent)
        startActivityForResult(intent, NoteDetailsActivity.REQUEST_EDIT_NOTE)

    }
}
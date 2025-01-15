package com.example.fragments.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.model.Notes
import com.example.fragments.OnFragmentDataListener
import com.example.fragments.R
import com.example.fragments.utils.CustomAdapter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainFragment : Fragment() {

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var count = 0
    private val listNote = mutableListOf<Notes>()
    private var adapter: CustomAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @SuppressLint("NewApi", "NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("BEGIN","START/RESTART")
        Log.d("TAG","старый лист ${listNote.size}")

        val savedCount = savedInstanceState?.getInt("count")
        if(savedCount != null){
            count = savedCount
        }
        val noteTextET: EditText = view.findViewById(R.id.inputET)
        noteTextET.requestFocus()
        val addBTN: Button = view.findViewById(R.id.addBTN)
        val recycleView: RecyclerView = view.findViewById(R.id.recycleViewRV)

        val savedNotes = savedInstanceState?.getParcelableArrayList<Notes>("notes")
        if (savedNotes != null) {
            listNote.clear()
            listNote.addAll(savedNotes)
            Log.d("TAG","Получение данных из Bundle")
            Log.d("TAG","общее количество ${listNote.size}")
        }
        onFragmentDataListener = requireActivity() as OnFragmentDataListener

        adapter = CustomAdapter(listNote) { selectItem ->
            onFragmentDataListener.onData(selectItem)
        }
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        addBTN.setOnClickListener {
            val date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
            val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString()
            val note = Notes(count, noteTextET.text.toString(), date, time)
            listNote.add(note)
            adapter!!.notifyItemInserted(listNote.size - 1)
            count++
            noteTextET.hint = null
            noteTextET.text.clear()
            noteTextET.hint = "Введите текст"
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("notes", ArrayList(listNote))
        outState.putInt("count",count)
        Log.d("TAG","Передача данных в Bundle")
        Log.d("TAG","${outState.getParcelableArrayList<Notes>("notes")}")
    }
//        val newNotes = arguments?.getString("newNote")
//        Log.d("GET", "$newNotes")
//        val oldNotes = arguments?.getString("oldNote")
//        Log.d("GET", "$oldNotes")
//        if (newNotes != null) {
//            val index = listNote.indexOfFirst { it.noteText == oldNotes }
//            Log.d("INDEX", "$index")
//            Log.d("SIZE", "${listNote.size}")
//            if (index != -1) {
//                listNote[index].noteText = newNotes
//            }
//        }


}





package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.selects.select
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainFragment : Fragment() {

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var count = 0
    private val listNote = mutableListOf<Notes>()
    private var adapter:CustomAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val noteTextET: EditText = view.findViewById(R.id.inputET)
        val addBTN: Button = view.findViewById(R.id.addBTN)
        val recycleView: RecyclerView = view.findViewById(R.id.recycleViewRV)

        addBTN.setOnClickListener {
            val date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
            val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString()
            val note = Notes(count, noteTextET.text.toString(), date, time)
            listNote.add(note)
            recycleView.layoutManager = LinearLayoutManager(context)
            adapter = CustomAdapter(listNote){
                select -> onFragmentDataListener.onData(select)
            }
            recycleView.adapter = adapter
            count++
            noteTextET.text.clear()
        }

    }
}




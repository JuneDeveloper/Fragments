package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView


class DetailsFragment : Fragment() {

    private lateinit var detailsET:EditText
    private lateinit var detailsEditBTN:Button
    private var note:String? = "da"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_details, container, false)
        detailsET = view.findViewById(R.id.detailsET)
        detailsEditBTN = view.findViewById(R.id.detailsEditBTN)
        note = arguments?.getString("note")
        detailsET.setText(note)
        return view
    }
}
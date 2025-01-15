package com.example.fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.example.fragments.OnFragmentDataListener
import com.example.fragments.R


class DetailsFragment : Fragment(), OnFragmentDataListener {

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var detailsET:EditText
    private lateinit var detailsEditBTN:Button
    private var note:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_details, container, false)
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        detailsET = view.findViewById(R.id.detailsET)
        detailsEditBTN = view.findViewById(R.id.detailsEditBTN)
        note = arguments?.getString("key")
        detailsET.setText(note)
        detailsEditBTN.setOnClickListener {
            val editText = detailsET.text
            onData(editText.toString())
        }
        return view
    }

    override fun onData(data: String?) {
        note = data
        parentFragmentManager.popBackStack()
        val bundle = Bundle()
        bundle.putString("newNote",data)
        bundle.putString("oldNote",note)

        val transaction = this.fragmentManager?.beginTransaction()
        val mainFragment = MainFragment()
        mainFragment.arguments = bundle

        transaction?.replace(R.id.fragment_main,mainFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
}
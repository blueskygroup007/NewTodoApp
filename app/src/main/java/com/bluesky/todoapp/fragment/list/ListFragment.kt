package com.bluesky.todoapp.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bluesky.todoapp.R
import com.bluesky.todoapp.databinding.FragmentListBinding


class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_list, container, false)
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.floatingActionButton.setOnClickListener() {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        binding.root.setOnClickListener() {

            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }
        return view
    }


}
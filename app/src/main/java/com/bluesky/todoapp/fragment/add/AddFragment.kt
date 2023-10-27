package com.bluesky.todoapp.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.data.viewmodel.SharedViewModel
import com.bluesky.todoapp.data.viewmodel.TodoViewModel
import com.bluesky.todoapp.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    val todoViewModel: TodoViewModel by viewModels()
    val sharedViewModel: SharedViewModel by viewModels()
    lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add, container, false)
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_add, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.item_menu_fragment_add_check -> insertDataToDb()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding.spUpdateFragment.onItemSelectedListener = sharedViewModel.listener
    }


    private fun insertDataToDb() {
        val title = binding.etTitleUpdateFragment.text.toString()
        val description = binding.etDescUpdateFragment.text.toString()
        val priority = binding.spUpdateFragment.selectedItemPosition
        val validation = sharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val newData = ToDoData(
                0,
                title = title,
                priority = sharedViewModel.parseIntToPriority(priority),
                description = description
            )
            todoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "new data added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "please fill out all data!", Toast.LENGTH_SHORT).show()
        }

    }


}
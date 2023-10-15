package com.bluesky.todoapp.fragment.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.data.viewmodel.SharedViewModel
import com.bluesky.todoapp.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    val mSharedViewModel: SharedViewModel by viewModels()
    lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_update, container, false)
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_update, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        val todoData = arguments?.getSerializable("TodoData") as ToDoData
        binding.etTitleUpdateFragment.setText(todoData.title)
        binding.etDescUpdateFragment.setText(todoData.description)
        binding.spUpdateFragment.setSelection(parsePriority(todoData.priority))
        binding.spUpdateFragment.onItemSelectedListener = mSharedViewModel.listener
    }

    private fun parsePriority(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}
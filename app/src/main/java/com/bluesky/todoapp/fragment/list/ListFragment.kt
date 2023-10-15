package com.bluesky.todoapp.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.viewmodel.TodoViewModel
import com.bluesky.todoapp.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private val mTodoViewModel: TodoViewModel by viewModels()
    private val mAdapter: ListAdapter by lazy { ListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        binding.root.setOnClickListener {

            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.setHasFixedSize(true)
        binding.rvList.adapter = mAdapter
/*        binding.rvList.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            //findNavController().navigate(R.id.action_listFragment_to_updateFragment)
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                rv.findNavController().navigate(R.id.action_listFragment_to_updateFragment)
                return true
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

        })*/

        mTodoViewModel.todoData.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }

        return view
    }

    /*menu新方法;setHasOptionsMenu已废弃*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}
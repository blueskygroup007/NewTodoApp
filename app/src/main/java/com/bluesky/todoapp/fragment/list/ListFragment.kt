package com.bluesky.todoapp.fragment.list

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.lifecycleOwner=this
        binding.viewmodel=mTodoViewModel


        val view = binding.root
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.setHasFixedSize(true)
        binding.rvList.adapter = mAdapter


        mTodoViewModel.allTodoData.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
            mTodoViewModel.todoDataCount.value=it.size
        }

        /*Todo 改用databinding的自定义属性nodataAdapter来实现了*/
        /*mTodoViewModel.todoDataCount.observe(viewLifecycleOwner) {
            binding.ivNoData.visibility = if (it == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.tvNoData.visibility = if (it == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }*/
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
                when (menuItem.itemId) {
                    R.id.item_menu_delete_all -> {
                        deleteAllitem()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun deleteAllitem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            mTodoViewModel.deleteAllData()
            Toast.makeText(requireContext(), "removed successfully!", Toast.LENGTH_SHORT).show()
            //findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                //这个如果什么也不写,会报错.正好写关闭提示窗口
                dialog?.dismiss()
            }
        })

        builder.setTitle("Delete All data?")
        builder.setMessage("Are you sure you want to remove all data?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        /*binding=null 有没有必要*/
    }
}
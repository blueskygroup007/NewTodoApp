package com.bluesky.todoapp.fragment.update

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bluesky.todoapp.R
import com.bluesky.todoapp.data.models.Priority
import com.bluesky.todoapp.data.models.ToDoData
import com.bluesky.todoapp.data.repository.TodoRepository
import com.bluesky.todoapp.data.viewmodel.SharedViewModel
import com.bluesky.todoapp.data.viewmodel.TodoViewModel
import com.bluesky.todoapp.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    val mSharedViewModel: SharedViewModel by viewModels()
    lateinit var binding: FragmentUpdateBinding
    val todoData: UpdateFragmentArgs by navArgs()
    val mTodoViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_update, container, false)
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner=viewLifecycleOwner
        binding.args=todoData.currentItemData
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
                when (menuItem.itemId) {
                    R.id.item_menu_fragment_update_save -> updateItem()
                    R.id.item_menu_fragment_update_delete -> deleteItem()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        /* 常规方法获取传递过来的参数*/
        /*val todoData = arguments?.getSerializable("TodoData") as ToDoData
               binding.etTitleUpdateFragment.setText(todoData.title)
                binding.etDescUpdateFragment.setText(todoData.description)
                binding.spUpdateFragment.setSelection(parsePriority(todoData.priority))*/

        /* 改为databinding方式显示*/
        /*binding.etTitleUpdateFragment.setText(todoData.currentItemData.title)
        binding.etDescUpdateFragment.setText(todoData.currentItemData.description)
        binding.spUpdateFragment.setSelection(mSharedViewModel.parsePriorityToInt(todoData.currentItemData.priority))*/

        binding.spUpdateFragment.onItemSelectedListener = mSharedViewModel.listener
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            mTodoViewModel.deleteData(todoData.currentItemData)
            Toast.makeText(requireContext(), "removed successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                //这个如果什么也不写,会报错.正好写关闭提示窗口
                dialog?.dismiss()
            }
        })

        builder.setTitle("Delete '${todoData.currentItemData.title}'?")
        builder.setMessage("Are you sure you want to remove '${todoData.currentItemData.title}'?")
        builder.create().show()

    }

    private fun updateItem() {
        val title = binding.etTitleUpdateFragment.text.toString()
        val desc = binding.etDescUpdateFragment.text.toString()
        val priority =
            mSharedViewModel.parseIntToPriority(binding.spUpdateFragment.selectedItemPosition)
        val validation = mSharedViewModel.verifyDataFromUser(title, desc)
        if (validation) {
            val updateItem = ToDoData(
                id = todoData.currentItemData.id,
                title = title,
                description = desc,
                priority = priority
            )
            mTodoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "update successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "update failed!", Toast.LENGTH_SHORT).show()

        }
    }


}
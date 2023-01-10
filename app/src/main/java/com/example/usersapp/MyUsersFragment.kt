package com.example.usersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyUsersFragment : Fragment(), MyRecyclerView.OnUserClickListener, MyContract.View {

    private val myPresenter: MyUsersPresenter by lazy {
        MyUsersPresenter()
    }
    private val myAdapter = MyRecyclerView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_users, container, false).apply {
            val floatingActionButton = this.findViewById<FloatingActionButton>(R.id.floating_action_button)
            val recyclerView = this.findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.adapter = myAdapter
            myAdapter.onUserClickListener = this@MyUsersFragment
            myAdapter.myList = myPresenter.getAllUsers()
            myAdapter.notifyDataSetChanged()
            floatingActionButton.setOnClickListener {
                addNewUser()
            }
        }
    }

    override fun showMyUsers(list: List<User>) {
        myAdapter.myList = myPresenter.getAllUsers()
        myAdapter.notifyDataSetChanged()
    }

    override fun onUserClick(user: User) {
        removeCurrentUser(user)
    }

    override fun removeCurrentUser(user: User) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_format).format(user.name, user.secondName))
            .setPositiveButton(R.string.yes_button) { _, _ -> myPresenter.deleteSelectedUser(user) }
            .create()
            .show()
    }

    override fun addNewUser() {
        val dialogView = layoutInflater.inflate(R.layout.item_add_new_user, null) as ViewGroup
        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton(R.string.add_new_user) { _, _ ->
                val name = dialogView.findViewById<EditText>(R.id.edit_text_name).text.toString()
                val surname = dialogView.findViewById<EditText>(R.id.edit_text_second_name).text.toString()
                myPresenter.addNewUser(name, surname)
            }
            .create()
            .show()
    }
}
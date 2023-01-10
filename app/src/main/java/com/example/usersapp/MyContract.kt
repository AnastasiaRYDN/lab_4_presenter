package com.example.usersapp

interface MyContract {

    interface View {
        fun showMyUsers(list: List<User>)
        fun removeCurrentUser(user: User)
        fun addNewUser()
    }

    interface Presenter {
        fun addNewUser(name: String, surname: String)
        fun deleteSelectedUser(user: User)
        fun getAllUsers(): List<User>
    }
}
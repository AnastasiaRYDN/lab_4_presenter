package com.example.usersapp

class MyUsersPresenter : MyContract.Presenter {

    private val myRepository = MyUsersRepository()

    private val myView: MyUsersFragment by lazy {
        MyUsersFragment()
    }

    override fun deleteSelectedUser(user: User) {
        myView.showMyUsers(myRepository.removeUser(user))
    }

    override fun getAllUsers(): List<User> {
        return myRepository.getUsers()
    }

    override fun addNewUser(name: String, surname: String) {
        myRepository.addUser(name, surname)
    }

}
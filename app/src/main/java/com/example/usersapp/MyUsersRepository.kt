package com.example.usersapp

class MyUsersRepository {

    var myUsers = mutableListOf<User>()
    private var myIncrementer = 0

    init {
        for (i in 0 until 50) {
            addUser(
                User(
                    id = myIncrementer,
                    name = if (myIncrementer % 2 == 0) "Bob $i" else "Kesha $i",
                    secondName = if (myIncrementer % 2 == 0) "Bobinsky $i" else "Stevenson $i"
                )
            )
        }
    }

    fun removeUser(user: User): List<User> {
        myUsers.remove(user)
        return myUsers.toList()
    }

    fun getUsers(): List<User> = myUsers

    fun addUser(name: String, surname: String) {
        myUsers.add(User(myIncrementer, name, surname))
        myIncrementer++
    }

    private fun addUser(user: User) {
        myUsers.add(element = user)
        myIncrementer++
    }


}
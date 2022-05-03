package com.hexad.library.service

import com.hexad.library.model.Library
import com.hexad.library.model.UserAccount

class LibraryUserServiceImpl: LibraryUserService {
    override fun borrowBook(name: String) {
        UserAccount.checkIfBookCanBeAdded()
        Library.checkIfBookCanBeBorrowed(name)

        Library.borrowBook(name)
        UserAccount.addBook(name)
    }

    override fun returnBook(name: String) {
        TODO("Not yet implemented")
    }
}
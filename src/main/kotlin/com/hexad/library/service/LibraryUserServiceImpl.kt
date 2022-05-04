package com.hexad.library.service

import com.hexad.library.model.Library
import com.hexad.library.model.UserAccount

class LibraryUserServiceImpl: LibraryUserService {
    override fun borrowBook(name: String) {
        UserAccount.checkLimit()
        Library.checkIfBookCanBeBorrowed(name)

        Library.borrowBook(name)
        UserAccount.addBook(name)
    }

    override fun returnBook(name: String) {
        UserAccount.returnBook(name)

    }
}
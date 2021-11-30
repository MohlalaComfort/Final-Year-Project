package com.example.epolicemanagementsystem.report_screen

class DatabaseModel() {
    lateinit var idNo : String
    lateinit var name_surname: String
    lateinit var contacts : String
    lateinit var description: String

    constructor(idNo: String,name_surname: String,contacts: String,description: String) : this() {
        this.idNo = idNo
        this.name_surname = name_surname
        this.contacts = contacts
        this.description = description
    }
}
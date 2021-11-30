package com.example.administrator.chatScreen

import android.content.IntentSender
import android.content.LocusId
class Message {
    var message: String? = null
    var senderId: String? = null

    constructor(){}

    constructor(message: String?, senderId: String?){
        this.message = message
        this.senderId = senderId
    }
}
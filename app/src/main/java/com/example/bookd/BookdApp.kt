package com.example.bookd

import android.app.Application
import com.example.bookd.data.local.AppDatabase

class BookdApp : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}

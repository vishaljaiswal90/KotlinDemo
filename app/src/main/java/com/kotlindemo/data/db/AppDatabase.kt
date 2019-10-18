package com.kotlindemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlindemo.data.db.entity.User
import java.util.concurrent.locks.Lock

@Database(
    entities = [User :: class],
    version = 1
)
abstract class AppDatabase :RoomDatabase(){

    abstract fun getUserDao () : UserDao

    companion object {

        // this variable to imidated visible to all thread
        @Volatile
        private var instance :AppDatabase? = null
        private val Lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(Lock){
            instance?:buildDatabase(context).also {
                instance = it
            }

        }

        private fun buildDatabase(context : Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase :: class.java,
                "MyDatabase.db"

            ).build()

    }
}
package com.alix01z.noteappkotlin_mvvm.hilt.module

import android.app.Application
import androidx.room.Room
import com.alix01z.noteappkotlin_mvvm.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
//InstallIn: Shows Scope (ActivityComponent , SingletonComponent , ...)
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application):AppDatabase =
        Room.databaseBuilder(application , AppDatabase::class.java , "NotesDB")
            .fallbackToDestructiveMigration()
            .build()
}
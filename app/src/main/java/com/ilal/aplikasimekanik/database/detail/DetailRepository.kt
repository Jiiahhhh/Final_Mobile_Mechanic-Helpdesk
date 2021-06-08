package com.ilal.aplikasimekanik.database.detail

import android.app.Application
import androidx.lifecycle.LiveData
import com.ilal.aplikasimekanik.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DetailRepository(application: Application) {
    private val detailDao: DetailDao?
    private var details: LiveData<List<Detail>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        detailDao = db?.detailDao()
        details = detailDao?.getDetail()
    }

    fun getDetails(): LiveData<List<Detail>>? {
        return details
    }

    fun insert(detail: Detail) = runBlocking {
        this.launch(Dispatchers.IO){
            detailDao?.insertDetail(detail)
        }
    }

    fun delete (detail: Detail){
        runBlocking {
            this.launch(Dispatchers.IO){
                detailDao?.deleteDetail(detail)
            }
        }
    }

    fun update(detail: Detail) = runBlocking {
        this.launch(Dispatchers.IO){
            detailDao?.updateDetail(detail)
        }
    }
}
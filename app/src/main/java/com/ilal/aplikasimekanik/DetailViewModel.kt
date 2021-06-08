package com.ilal.aplikasimekanik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ilal.aplikasimekanik.database.detail.Detail
import com.ilal.aplikasimekanik.database.detail.DetailRepository

class DetailViewModel (application: Application): AndroidViewModel(application) {
    private var detailRepository = DetailRepository(application)
    private var details: LiveData<List<Detail>>? = detailRepository.getDetails()

    fun insertDetail(detail: Detail){
        detailRepository.insert(detail)
    }

    fun getDetail(): LiveData<List<Detail>>? {
        return details
    }

    fun deleteDetail(detail: Detail){
        detailRepository.delete(detail)
    }

    fun updateDetail(detail: Detail){
        detailRepository.update(detail)
    }
}
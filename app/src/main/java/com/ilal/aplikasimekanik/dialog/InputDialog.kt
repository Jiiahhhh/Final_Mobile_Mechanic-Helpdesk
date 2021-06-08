package com.ilal.aplikasimekanik.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ilal.aplikasimekanik.DetailAdapter
import com.ilal.aplikasimekanik.DetailViewModel
import com.ilal.aplikasimekanik.R
import com.ilal.aplikasimekanik.database.detail.Detail
import kotlinx.android.synthetic.main.input_dialog.*

class InputDialog : DialogFragment() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        return inflater.inflate(R.layout.input_dialog,container,false)
    }

    private fun addData() {
        detailViewModel.insertDetail(
            Detail(namaMekanik = edtNama.text.toString(),
            jenisMotor = edtJenis.text.toString(),
            kondisiMotor = edtKondisi.text.toString())
        )
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
//        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        btnSimpan.setOnClickListener {
            addData()
            dialog!!.dismiss()
        }
    }
}

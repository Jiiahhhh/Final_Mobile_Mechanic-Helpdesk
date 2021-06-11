package com.ilal.aplikasimekanik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilal.aplikasimekanik.database.detail.Detail
import com.ilal.aplikasimekanik.dialog.InputDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailAdapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener{
            InputDialog()
                .show(supportFragmentManager, "My")
        }

        btnBack.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Konfirmasi Keluar")
            alert.setMessage("Apakah Anda yakin ingin keluar dari Aplikasi?")

            alert.setPositiveButton("Yakin"){dialog, _ ->
                finish()
            }
            alert.setNegativeButton("Tidak"){dialog, _ ->
                dialog.dismiss()
            }
            alert.show()
        }

        rvDetail.layoutManager = LinearLayoutManager(this)
        detailAdapter = DetailAdapter(this){ detail, i ->
            showAlertMenu(detail)
        }
        rvDetail.adapter = detailAdapter

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.getDetail()?.observe(this, Observer {
            detailAdapter.setDetails(it)
        })
    }

    private fun showAlertMenu(detail: Detail) {
        val items = arrayOf("Edit", "Delete", "Share")

        val builder = AlertDialog.Builder(this)
        builder.setItems(items){dialog, which ->
            when(which){
                0 -> {
                    showAlertDialogEdit(detail)
                }
                1 -> {
                    detailViewModel.deleteDetail(detail)
                }
                2 -> {
                    share(detail)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(detail: Detail){
        val alert = AlertDialog.Builder(this)

        val edtDetail = EditText(applicationContext)
        edtDetail.setText(detail.solusiMotor)

        alert.setTitle("Edit Detail")
        alert.setView(edtDetail)

        alert.setPositiveButton("Update"){dialog, _ ->
            detail.solusiMotor = edtDetail.text.toString()
            detailViewModel.updateDetail(detail)
            dialog.dismiss()
        }
        alert.setNegativeButton("Cancel"){dialog, _ ->
            dialog.dismiss()
        }
        alert.show()
    }

    private fun share(detail: Detail){
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Terkait permasalahan ${detail.masalahMotor}, ${detail.namaMekanik} menyarankan " +
                    "'${detail.solusiMotor}'")
            type = "text/plain"
        }
        val i = Intent.createChooser(intent, null)
        startActivity(i)
    }
}
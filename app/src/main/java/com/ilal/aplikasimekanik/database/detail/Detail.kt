package com.ilal.aplikasimekanik.database.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail")
data class Detail (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "Nama Mekanik")
    var namaMekanik: String,
    @ColumnInfo(name = "Masalah Motor")
    var masalahMotor: String,
    @ColumnInfo(name = "Solusi Motor")
    var solusiMotor: String
)
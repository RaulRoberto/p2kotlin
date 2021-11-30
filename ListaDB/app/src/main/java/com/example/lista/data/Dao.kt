package com.example.lista.data

import androidx.room.*
import androidx.room.Dao
import com.example.lista.model.Item

@Dao
interface Dao {

    // CRUD

    // Create (Incluir)
    @Insert
    fun incluirItem(item: Item)

    // Read (Ler)
    @Query("SELECT * FROM item")
    fun obterItens(): MutableList<Item>

    // Update
    @Update
    fun alterarItem(item: Item)

    // Delete
    @Delete
    fun excluirItem(item: Item)
}
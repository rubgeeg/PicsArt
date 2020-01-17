package com.theguardian.dao

import androidx.room.*
import com.theguardian.models.Response
import com.theguardian.models.Result

@Dao
interface ResponseDao {

    @Query("SELECT * FROM response")
    fun getResponses(): List<Response>

    @Query("SELECT * FROM response WHERE total == :totalNum")
    fun getResponse(totalNum: Long): Response

    @Query("SELECT * FROM result")
    fun getResults(): List<Result>

    @Query("SELECT * FROM result WHERE id == :id")
    fun getResult(id: String): Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResults(results: ArrayList<Result>)

    @Update
    fun updateResult(result: Result)

    @Insert
    fun insertResponses(results: ArrayList<Response>)

  //  @Query("SELECT * FROM result WHERE isLiked == :removed ")
  //  fun getNotRemoved(removed:Boolean):List<Result>



    }
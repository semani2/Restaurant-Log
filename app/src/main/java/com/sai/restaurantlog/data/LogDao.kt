package com.sai.restaurantlog.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by sai on 1/5/18.
 */

@Dao interface LogDao {

    /**
     * Get all logs
     */
    @Query("SELECT * FROM Log")
    fun getAllLogs(): LiveData<List<Log>>

    /**
     * Get log by id
     * @param id = log id
     */
    @Query("SELECT * FROM Log WHERE id = :id")
    fun getLogById(id: Long): LiveData<Log>

    /**
     * Insert a new log item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLog(log: Log): Long

    /**
     * Delete log 
     */
    @Delete
    fun deleteLog(log: Log)
}
package com.yarkho.dictionary.feature_dictionary.date.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yarkho.dictionary.feature_dictionary.date.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract val dao: WordInfoDao
}
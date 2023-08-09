package com.yarkho.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.yarkho.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.yarkho.dictionary.feature_dictionary.data.remote.DictionaryAPI
import com.yarkho.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.yarkho.dictionary.feature_dictionary.data.util.GsonParser
import com.yarkho.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.yarkho.dictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetInfoUseCase(repository: WordInfoRepository): GetWordInfo{
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        database: WordInfoDatabase,
        api: DictionaryAPI
    ): WordInfoRepository{
        return WordInfoRepositoryImpl(api, database.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app:Application): WordInfoDatabase{
        return  Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db",
        ).addTypeConverter(GsonParser(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryAPI{
        return Retrofit.Builder()
            .baseUrl(DictionaryAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryAPI::class.java)
    }
}
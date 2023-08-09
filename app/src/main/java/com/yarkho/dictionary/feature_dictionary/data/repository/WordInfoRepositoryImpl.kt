package com.yarkho.dictionary.feature_dictionary.data.repository

import com.yarkho.dictionary.core.util.Resource
import com.yarkho.dictionary.feature_dictionary.data.local.WordInfoDao
import com.yarkho.dictionary.feature_dictionary.data.remote.DictionaryAPI
import com.yarkho.dictionary.feature_dictionary.domain.model.WordInfo
import com.yarkho.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryAPI,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(data= wordInfos))

        try{
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException){
            emit(Resource.Error(
                message = "Something went worng.",
                data = wordInfos
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Could not reach server, check internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}
package com.oechslerbernardo.diaryapp.data.repository

import com.oechslerbernardo.diaryapp.model.Diary
import com.oechslerbernardo.diaryapp.util.RequestState
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import java.time.LocalDate

typealias Diaries = RequestState<Map<LocalDate, List<Diary>>>

interface MongoRepository {

    fun configureTheRealm()
    fun getAllDiaries(): Flow<Diaries>
    fun getSelectedDiary(diaryId: ObjectId): RequestState<Diary>
    suspend fun addNewDiary(diary: Diary): RequestState<Diary>
}
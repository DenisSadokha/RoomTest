package com.example.sqltest.domain.features.musicSchoolInfo.use_cases

import com.example.sqltest.base.state.ResultState
import com.example.sqltest.data.features.musicSchoolInfo.repositories.SchoolInfoRepository
import com.example.sqltest.domain.features.musicSchoolInfo.entities.AllMembersMuSchool
import com.example.sqltest.domain.features.musicSchoolInfo.entities.CourseEntity
import com.example.sqltest.domain.features.musicSchoolInfo.entities.MusicSchoolEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SchoolUseCase @Inject constructor(val repository: SchoolInfoRepository) {

    suspend fun getMusicSchool(): Flow<ResultState<MusicSchoolEntity>> {
     return    repository.getMusicSchool()

    }

    suspend fun insertStudentToSchool(studentId: Int,
                                      teacherId: Int,
                                      courseId: Int) =
        repository.insertStudentToSchool(studentId, teacherId, courseId)


    suspend fun deleteStudentFromSchool(id: Int) = repository.deleteStudentFromSchool(id)

    suspend fun getAllMembers():  Flow<ResultState<AllMembersMuSchool>> = repository.getAllMembers()


}
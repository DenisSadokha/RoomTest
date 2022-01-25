package com.example.sqltest.domain.features.musicSchoolInfo.use_cases

import com.example.sqltest.base.state.ResultState
import com.example.sqltest.data.features.musicSchoolInfo.repositories.SchoolInfoRepository
import com.example.sqltest.domain.features.musicSchoolInfo.entities.CourseEntity
import com.example.sqltest.domain.features.musicSchoolInfo.entities.StudentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentUseCase @Inject constructor( val repository: SchoolInfoRepository) {

    suspend fun getStudents(): Flow<ResultState<StudentEntity>> = repository.getStudents()

    suspend fun insertStudent(startEducation: String, name: String) =
        repository.insertStudent(startEducation, name)

    suspend fun deleteStudentById(id: Int) {
        repository.deleteStudentByIdInMainTable(id)
        repository.deleteStudentById(id)
    }

    suspend fun editStudent(id: Int, startEducation: String, name: String) =
        repository.editStudent(id, startEducation, name)
}
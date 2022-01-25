package com.example.sqltest.data.features.musicSchoolInfo.repositories

import com.example.sqltest.base.state.ErrorState
import com.example.sqltest.base.state.LoadingState
import com.example.sqltest.base.state.ResultState
import com.example.sqltest.base.state.SuccessState
import com.example.sqltest.data.features.musicSchoolInfo.datasource.MusicSchoolDataSource
import com.example.sqltest.data.features.musicSchoolInfo.mapper.toEntity
import com.example.sqltest.domain.features.musicSchoolInfo.entities.*
import com.example.sqltest.localStorage.models.TeacherModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolInfoRepository @Inject constructor(val api: MusicSchoolDataSource) {
    suspend fun getCourses(isSorted: Boolean): Flow<ResultState<CourseEntity>> {
        return flow<ResultState<CourseEntity>> {
            emit(
                ResultState(
                    state = LoadingState(loading = true),
                    entity = null
                )
            )

            val response = if (isSorted) api.getSortedByNameCourses()
            else api.getAllCourses()

            if (response.isNullOrEmpty()) {
                emit(ResultState(ErrorState(228), null))
            } else {
                emit(
                    ResultState(
                        state = SuccessState(
                            200
                        ), response.toEntity()
                    )
                )
            }


        }


    }



    suspend fun getAllMembers(): Flow<ResultState<AllMembersMuSchool>> {
        return flow<ResultState<AllMembersMuSchool>> {
            emit(
                ResultState(
                    state = LoadingState(loading = true),
                    entity = null
                )
            )
            val responseTeacher = api.getAllTeachers()
            val responseStudent = api.getAllStudent()
            val responseCourse = api.getAllCourses()

            if (responseTeacher.isNullOrEmpty() && responseStudent.isNullOrEmpty() && responseCourse.isNullOrEmpty()) {
                emit(ResultState(ErrorState(228), null))
            } else {
                val courseEntity = responseCourse.toEntity()
                val studentEntity = responseStudent.toEntity()
                val teacherEntity = responseTeacher.toEntity()
                emit(
                    ResultState(
                        state = SuccessState(
                            200
                        ), AllMembersMuSchool(
                            student = studentEntity,
                            teacher = teacherEntity,
                            course = courseEntity
                        )
                    )
                )
            }

        }
    }


    suspend fun getStudents(): Flow<ResultState<StudentEntity>> {
        return flow<ResultState<StudentEntity>> {
            emit(
                ResultState(
                    state = LoadingState(loading = true),
                    entity = null
                )
            )
            val responseName = api.getStudentsNameAndTeachersName()
            val response = api.getAllStudent()
            if (response.isNullOrEmpty()) {
                emit(ResultState(ErrorState(228), null))
            } else {
                emit(
                    ResultState(
                        state = SuccessState(
                            200
                        ), response.toEntity(responseName)
                    )
                )

            }


        }


    }

    suspend fun getTeachers(onlyLaureate: Boolean): Flow<ResultState<TeacherEntity>> {
        return flow<ResultState<TeacherEntity>> {
            emit(
                ResultState(
                    state = LoadingState(loading = true),
                    entity = null
                )
            )
            var response = listOf<TeacherModel>()
            response = if (onlyLaureate)
                api.getLaureateTeachers()
            else
                api.getAllTeachers()

            if (response.isNullOrEmpty()) {
                emit(ResultState(ErrorState(228), null))
            } else {
                emit(
                    ResultState(
                        state = SuccessState(
                            200
                        ), response.toEntity()
                    )
                )

            }


        }


    }


    suspend fun getMusicSchool(): Flow<ResultState<MusicSchoolEntity>> {
        return flow<ResultState<MusicSchoolEntity>> {
            emit(
                ResultState(
                    state = LoadingState(loading = true),
                    entity = null
                )
            )


            val response = api.getAllSchoolItem()

            val responseJoin = api.usesJoin()

            val responseCount = api.getCountSchoolStudent()

            val students: MutableList<Student> = mutableListOf()
            val courses: MutableList<Course> = mutableListOf()
            val teachers: MutableList<Teacher> = mutableListOf()

            response.forEach {
                students.add(api.getStudentById(it.studentId).toEntity())
                courses.add(api.getCourseById(it.courseId).toEntity())
                teachers.add(api.getTeacherById(it.teacherId).toEntity())
            }
            val parsedResponse = mutableListOf<MusicsSchoolStudent>()

            for (i in response.indices) {
                parsedResponse.add(
                    MusicsSchoolStudent(
                        response[i].id,
                        students[i],
                        teachers[i],
                        courses[i]
                    )
                )
            }



            if (response.isNullOrEmpty()) {
                emit(ResultState(ErrorState(228), null))
            } else {
                emit(
                    ResultState(
                        state = SuccessState(
                            200
                        ), MusicSchoolEntity(parsedResponse, responseCount, responseJoin)
                    )
                )

            }


        }


    }

    suspend fun insertCourse(educationDuration: String, name: String) {
        api.insertCourse(educationDuration, name)
    }

    suspend fun editCourse(id: Int, educationDuration: String, name: String) {
        api.editCourse(id, educationDuration, name)
    }

    suspend fun deleteCourseById(id: Int) {
        api.deleteCourseById(id)
    }

    suspend fun insertStudent(
        startEducation: String,
        name: String
    ) {
        api.insertStudent(startEducation, name)
    }

    suspend fun editStudent(
        id: Int, startEducation: String,
        name: String
    ) {
        api.editStudent(id, startEducation, name)
    }

    suspend fun deleteStudentById(id: Int) {
        api.deleteStudentById(id)
    }

    suspend fun insertTeacher(
        laureateDe: Boolean,
        name: String
    ) {
        api.insertTeacher(laureateDe, name)
    }

    suspend fun editTeacher(
        id: Int, laureateDe: Boolean,
        name: String
    ) {
        api.editTeacher(id, laureateDe, name)
    }

    suspend fun deleteTeacherById(id: Int) {
        api.deleteTeacherById(id)
    }

    suspend fun insertStudentToSchool(
        studentId: Int,
        teacherId: Int,
        courseId: Int
    ) {
        api.insertStudentToSchool(studentId, teacherId, courseId)
    }


    suspend fun deleteStudentFromSchool(id: Int) {
        api.deleteStudentFromSchool(id)
    }

    suspend fun deleteCourseIdInMainTable(id: Int) {
        api.deleteCourseByIdInMainTable(id)
    }

    suspend fun deleteTeacherByIdInMainTable(id: Int) {
        api.deleteTeacherByIdInMainTable(id)
    }

    suspend fun deleteStudentByIdInMainTable(id: Int) {
        api.deleteStudentByIdInMainTable(id)
    }

    suspend fun getStudentById(id: Int): Student {
        val resp = api.getStudentById(id)
        return Student(
            id = resp.id,
            startEducation = resp.startEducation,
            name = resp.name
        )
    }

    suspend fun getTeacherById(id: Int): Teacher {
        val resp = api.getTeacherById(id)
        return Teacher(
            id = resp.id,
            laureateDegree = resp.laureateDegree,
            name = resp.name
        )
    }

    suspend fun getCourseById(id: Int): Course {
        val resp = api.getCourseById(id)
        return Course(
            id = resp.id,
            courseDuration = resp.educationDuration,
            name = resp.name
        )
    }

}
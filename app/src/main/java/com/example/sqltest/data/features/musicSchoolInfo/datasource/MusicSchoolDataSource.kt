package com.example.sqltest.data.features.musicSchoolInfo.datasource

import com.example.sqltest.localStorage.dao.MainDao
import com.example.sqltest.localStorage.models.CourseModel
import com.example.sqltest.localStorage.models.MusicalSchoolModel
import com.example.sqltest.localStorage.models.StudentModel
import com.example.sqltest.localStorage.models.TeacherModel
import javax.inject.Inject

class MusicSchoolDataSource @Inject constructor(val dao: MainDao) {


    suspend fun getAllCourses(): List<CourseModel> = dao.getAllCourses()

    suspend fun getAllStudent(): List<StudentModel> = dao.getAllStudent()

    suspend fun getAllTeachers(): List<TeacherModel> = dao.getAllTeachers()

    suspend fun getLaureateTeachers(): List<TeacherModel> = dao.getLaureateTeachers()

    suspend fun getSortedByNameCourses(): List<CourseModel> = dao.getSortedByNameCourses()

    suspend fun getCountSchoolStudent(): Int = dao.getCountStudentsInSchool()

    suspend fun getAllSchoolItem(): List<MusicalSchoolModel> = dao.getAllSchoolItem()

    suspend fun getStudentsByName(name: String): List<StudentModel> = dao.getStudentsByName(name)

    suspend fun getStudentsNameAndTeachersName(): List<String> = dao.getStudentsNameAndTeachersName()

    suspend fun usesJoin(): List<String> =  dao.usesJoin()

    suspend fun insertCourse(educationDuration: String, name: String) {
        try {
            dao.insertCourse(educationDuration, name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun insertTeacher(laureateDe: Boolean, name: String) {
        try {
            dao.insertTeacher(laureateDe, name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun insertStudent(startEducation: String, name: String) {
        try {
            dao.insertStudent(startEducation, name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun insertStudentToSchool(studentId: Int, teacherId: Int, courseId: Int) {
        try {
            dao.insertStudentToSchool(studentId, teacherId, courseId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    suspend fun editCourse(id: Int, educationDuration: String, name: String) {
        dao.editCourse(id, educationDuration, name)
    }

    suspend fun editStudent(
        id: Int, startEducation: String,
        name: String
    ) {
        dao.editStudent(id, startEducation, name)
    }

    suspend fun editTeacher(
        id: Int, newLaureateDegree: Boolean,
        name: String
    ) {
        dao.editTeacher(id, newLaureateDegree, name)
    }

    suspend fun deleteCourseById(id: Int) {
        dao.deleteCourseById(id)
    }

    suspend fun deleteCourseByIdInMainTable(id: Int) {
        dao.deleteCourseByIdInMainTable(id)
    }

    suspend fun deleteStudentByIdInMainTable(id: Int) {
        dao.deleteStudentByIdInMainTable(id)
    }

    suspend fun deleteTeacherByIdInMainTable(id: Int) {
        dao.deleteTeacherByIdInMainTable(id)
    }

    suspend fun deleteStudentById(id: Int) {
        dao.deleteStudentById(id)
    }

    suspend fun deleteTeacherById(id: Int) {
        dao.deleteTeacherById(id)
    }

    suspend fun deleteStudentFromSchool(id: Int) {
        dao.deleteStudentFromSchool(id)
    }

    suspend fun getStudentById(id: Int): StudentModel = dao.getStudentById(id)

    suspend fun getCourseById(id: Int): CourseModel = dao.getCourseById(id)

    suspend fun getTeacherById(id: Int): TeacherModel = dao.getTeacherById(id)




}
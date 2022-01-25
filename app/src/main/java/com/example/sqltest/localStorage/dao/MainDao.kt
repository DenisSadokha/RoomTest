package com.example.sqltest.localStorage.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.sqltest.app.utils.StudentTable
import com.example.sqltest.app.utils.TableNames
import com.example.sqltest.app.utils.TeacherTable
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Course
import com.example.sqltest.localStorage.models.CourseModel
import com.example.sqltest.localStorage.models.MusicalSchoolModel
import com.example.sqltest.localStorage.models.StudentModel
import com.example.sqltest.localStorage.models.TeacherModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Dao
interface MainDao {


    // for teachers table query
    @Query("INSERT INTO ${TableNames.TEACHER_TABLE} (${TeacherTable.laureateDegree}, ${TeacherTable.name}) VALUES (:laureateDegree, :name)")
    suspend fun insertTeacher(laureateDegree: Boolean, name: String)

    @Query("SELECT * FROM ${TableNames.TEACHER_TABLE} WHERE ${TeacherTable.laureateDegree} = '1'")
    suspend fun getLaureateTeachers(): List<TeacherModel>

    @Query("DELETE FROM ${TableNames.TEACHER_TABLE} WHERE ${TeacherTable.id} = :id")
    suspend fun deleteTeacherById(id: Int)

    @Query("SELECT * FROM teachers WHERE id = :id")
    suspend fun getTeacherById(id: Int) : TeacherModel

    @Query("UPDATE teachers SET laureateDegree = :newLaureateDegree, name = :name WHERE id = :id")
    suspend fun editTeacher(id: Int, newLaureateDegree: Boolean, name: String)

    @Query("SELECT * FROM teachers")
    suspend fun getAllTeachers() : List<TeacherModel>




    // For student table query
    @Query("INSERT INTO ${TableNames.STUDENT_TABLE} (${StudentTable.startEducation}, ${StudentTable.name}) VALUES (:startEducation, :name)")
    suspend fun insertStudent(startEducation: String, name: String)

    @Query("SELECT * FROM students WHERE name LIKE :name || '%'")

    suspend fun getStudentsByName(name: String): List<StudentModel>

    @Query("SELECT * FROM students WHERE id = :id")
    suspend fun getStudentById(id: Int) : StudentModel

    @Query("SELECT * FROM students")
    suspend fun getAllStudent() : List<StudentModel>

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteStudentById(id: Int)

    @Query("UPDATE students SET startEducation = :startEducation, name = :name WHERE id = :id")
    suspend fun editStudent(id: Int, startEducation: String, name: String)




    // For Course table query
    @Query("INSERT INTO courses (educationDuration, name) VALUES (:educationDuration, :name)")
    suspend fun insertCourse(educationDuration: String, name: String)

    @Query("SELECT * FROM courses")
    suspend fun getAllCourses(): List<CourseModel>

    @Query("SELECT * FROM courses WHERE id = :id")
    suspend fun getCourseById(id: Int) : CourseModel

    @Query("DELETE FROM courses WHERE id = :id")
    suspend fun deleteCourseById(id: Int)

    @Query("UPDATE courses SET educationDuration = :educationDuration, name = :name WHERE id = :id")
    suspend fun editCourse(id: Int, educationDuration: String, name: String)

    @Query("SELECT * FROM courses ORDER BY name")
    suspend fun getSortedByNameCourses(): List<CourseModel>

    // хр процед


//    CREATE PROCEDURE test_name(name: String) AS
//    SELECT courses.name FROM courses WHERE courses.name = :name LIMIT 5

    // order by asc-от низких к выс desc - наоборот


    // creating index for courses table
//    @Query("CREATE INDEX course_name_idx ON courses(name)")
//    suspend fun testCreateIndex()

    // CREATE TRIGGER triggr_name ON Course
    //  AFTER INSERT
    //  AS
    // UPDATE Course SET courseDuratuin = courseDuration  + 1 WHERE Id =(SELECT Id FROM inserted)



    // For school table
    @Query("INSERT INTO school (studentId, teacherId, courseId) VALUES (:studentId, :teacherId, :courseId)")
    suspend fun insertStudentToSchool(studentId: Int, teacherId: Int, courseId: Int)

    @Query("DELETE FROM school WHERE id = :id")
    suspend fun deleteStudentFromSchool(id: Int)

    @Query("DELETE FROM school WHERE courseId = :id")
    suspend fun deleteCourseByIdInMainTable(id: Int)

    @Query("DELETE FROM school WHERE studentId = :id")
    suspend fun deleteStudentByIdInMainTable(id: Int)

    @Query("DELETE FROM school WHERE teacherId = :id")
    suspend fun deleteTeacherByIdInMainTable(id: Int)

    @Query("SELECT * FROM school")
    suspend fun getAllSchoolItem(): List<MusicalSchoolModel>

    @Query("SELECT COUNT(*) FROM school")
    suspend fun getCountStudentsInSchool(): Int


    @Query("SELECT UPPER(name) FROM students UNION SELECT name FROM teachers")
    suspend fun getStudentsNameAndTeachersName(): List<String>

    @Query("SELECT students.name FROM school LEFT JOIN students ON students.id = studentId")
    suspend fun usesJoin(): List<String>




}
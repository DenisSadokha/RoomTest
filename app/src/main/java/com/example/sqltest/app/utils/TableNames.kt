package com.example.sqltest.app.utils

object TableNames {
    const val STUDENT_TABLE = "students"
    const val MAIN_TABLE = "school"
    const val COURSE_TABLE = "courses"
    const val TEACHER_TABLE = "teachers"
}

object StudentTable {
    const val id = "id"
    const val name = "name"
    const val startEducation = "startEducation"

}

object SchoolTable {
    const val id = "id"
    const val studentId = "studentId"
    const val teacherId = "teacherId"
    const val courseId = "courseId"

}

object TeacherTable {
    const val id = "id"
    const val name = "name"
    const val laureateDegree = "laureateDegree"

}

object CourseTable {
    const val id = "id"
    const val name = "name"
    const val educationDuration = "educationDuration"
}

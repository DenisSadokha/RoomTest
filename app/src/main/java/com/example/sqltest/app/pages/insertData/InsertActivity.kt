package com.example.sqltest.app.pages.insertData

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sqltest.app.pages.start.MainActivity
import com.example.sqltest.app.utils.appComponent
import com.example.sqltest.base.present.viewModelFactory
import com.example.sqltest.databinding.InsertActivityBinding
import com.example.sqltest.domain.features.musicSchoolInfo.entities.AllMembersMuSchool
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Course
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Student
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Teacher
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.CourseUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.SchoolUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.StudentUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.TeacherUseCase
import javax.inject.Inject


class InsertActivity : AppCompatActivity() {

    lateinit var binding: InsertActivityBinding


    @Inject
    lateinit var courseUseCase: CourseUseCase

    @Inject
    lateinit var teacherUseCase: TeacherUseCase

    @Inject
    lateinit var studentUseCase: StudentUseCase

    @Inject
    lateinit var schoolUseCase: SchoolUseCase

    private val viewModel: InsertDataViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory {
                InsertDataViewModel(
                    courseUseCase,
                    teacherUseCase,
                    studentUseCase,
                    schoolUseCase
                )
            }
        )[InsertDataViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        val bundle = intent.extras

        val entityCode = bundle?.getInt(MainActivity.CODE_OF_TABLE) ?: -1



        binding = DataBindingUtil.setContentView(this, com.example.sqltest.R.layout.insert_activity)


        showView(entityCode)

        fillField(entityCode, bundle)

        binding.btnSave.setOnClickListener {
            if (bundle?.getInt("isEdit") == 1) {
                editInfo(entityCode, bundle.getInt("id"))
            } else saveInfo(entityCode)
        }


    }


    private fun fillField(entityCode: Int, extras: Bundle?) {
        when (entityCode) {
            MainActivity.SCHOOL -> {
                viewModel.getAllMembers()
                viewModel.viewState.observe(this, { handleViewState(it) })


            }
            MainActivity.STUDENT -> {
                binding.etStudentName.setText(extras?.getString("name"))
                binding.etStudentStartEducation.setText(extras?.getString("startEducation"))
            }
            MainActivity.TEACHER -> {
                binding.etTeacherName.setText(extras?.getString("name"))
                binding.switchTeacherLaureate.isChecked = extras?.getBoolean("laureate") ?: false
            }
            MainActivity.COURSE -> {
                binding.etCourseName.setText(extras?.getString("name"))
                binding.etCourseDuration.setText(extras?.getString("duration"))


            }
        }
    }

    override fun onDestroy() {
        viewModel.viewState.removeObservers(this)
        super.onDestroy()
    }

    private fun handleViewState(state: InsertViewState) {
        when {
            state.loading -> {

            }
            state.success -> {
                setSpinnerData(state.entity as AllMembersMuSchool)
            }
        }

    }

    private fun setSpinnerData(data: AllMembersMuSchool) {
        val courseAdapter: ArrayAdapter<Course> =
            ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, data.course.courses)
        val studentAdapter: ArrayAdapter<Student> =
            ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, data.student.students)
        val teacherAdapter: ArrayAdapter<Teacher> =
            ArrayAdapter<Teacher>(this, android.R.layout.simple_list_item_1, data.teacher.teachers)
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.spCourses.adapter = courseAdapter
        binding.spStudent.adapter = studentAdapter
        binding.spTeachers.adapter = teacherAdapter


    }


    private fun saveInfo(entityCode: Int) {
        when (entityCode) {
            MainActivity.SCHOOL -> {
                try {

                    val courseId =
                        (binding.spCourses.getItemAtPosition(binding.spCourses.selectedItemPosition) as Course).id
                    val studentId =
                        (binding.spStudent.getItemAtPosition(binding.spStudent.selectedItemPosition) as Student).id
                    val teacherId =
                        (binding.spTeachers.getItemAtPosition(binding.spTeachers.selectedItemPosition) as Teacher).id
                    viewModel.putStudentToSchool(studentId, courseId, teacherId)
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
            MainActivity.STUDENT -> {
                try {
                    viewModel.putStudent(
                        binding.etStudentName.text.toString(),
                        binding.etStudentStartEducation.text.toString()
                    )
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
            MainActivity.TEACHER -> {
                try {
                    viewModel.putTeacher(
                        binding.etTeacherName.text.toString(),
                        binding.switchTeacherLaureate.isChecked
                    )
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
            MainActivity.COURSE -> {
                try {
                    viewModel.putCourse(
                        binding.etCourseName.text.toString(),
                        binding.etCourseDuration.text.toString()
                    )
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    private fun editInfo(entityCode: Int, id: Int) {
        when (entityCode) {
            MainActivity.STUDENT -> {
                try {
                    viewModel.editStudent(
                        id,
                        binding.etStudentName.text.toString(),
                        binding.etStudentStartEducation.text.toString()
                    )
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
            MainActivity.TEACHER -> {
                try {
                    viewModel.editTeacher(
                        id,
                        binding.etTeacherName.text.toString(),
                        binding.switchTeacherLaureate.isChecked
                    )
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
            MainActivity.COURSE -> {
                try {
                    viewModel.editCourse(
                        id,
                        binding.etCourseName.text.toString(),
                        binding.etCourseDuration.text.toString()
                    )
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent().putExtra(MainActivity.CODE_OF_TABLE, entityCode))
                    onBackPressed()

                } catch (e: Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showView(entityCode: Int) {
        when (entityCode) {
            MainActivity.SCHOOL -> {
                binding.viewGroupSchool.visibility = View.VISIBLE
            }
            MainActivity.STUDENT -> {
                binding.viewGroupStudent.visibility = View.VISIBLE
            }
            MainActivity.TEACHER -> {
                binding.viewGroupTeacher.visibility = View.VISIBLE
            }
            MainActivity.COURSE -> {
                binding.viewGroupCourse.visibility = View.VISIBLE

            }
        }

    }


}
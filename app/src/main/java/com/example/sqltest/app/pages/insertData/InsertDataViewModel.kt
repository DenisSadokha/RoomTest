package com.example.sqltest.app.pages.insertData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqltest.app.pages.showData.ShowViewState
import com.example.sqltest.base.state.ErrorState
import com.example.sqltest.base.state.LoadingState
import com.example.sqltest.base.state.SuccessState
import com.example.sqltest.domain.features.musicSchoolInfo.entities.AllMembersMuSchool
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.CourseUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.SchoolUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.StudentUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.TeacherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertDataViewModel(
    private val courseUseCase: CourseUseCase,
    private val teacherUseCase: TeacherUseCase,
    private val studentUseCase: StudentUseCase,
    private val schoolUseCase: SchoolUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<InsertViewState>()
    val viewState: LiveData<InsertViewState> = _viewState
    init {
        _viewState.value =
            InsertViewState(
                loading = false,
                success = false,
                error = false,
                entity = null
            )

    }


    fun putCourse(courseName: String, duration: String) {
        viewModelScope.launch(Dispatchers.IO) {
            courseUseCase.insertCourse(duration = duration, name = courseName)
        }
    }

    fun putTeacher(name: String, laureate: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherUseCase.insertTeacher(laureate, name)
        }
    }

    fun putStudent(name: String, startEducation: String) {
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCase.insertStudent(startEducation, name)
        }
    }
    fun putStudentToSchool(studentId: Int, courseId: Int, teacherId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            schoolUseCase.insertStudentToSchool(studentId, teacherId, courseId)
        }
    }

    fun editCourse(id: Int, courseName: String, duration: String) {
        viewModelScope.launch(Dispatchers.IO) {
            courseUseCase.editCourse(id, duration, courseName)
        }
    }

    fun editTeacher(id: Int, name: String, laureate: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherUseCase.editTeacher(id, laureate, name)
        }
    }

    fun editStudent(id: Int, name: String, startEducation: String) {
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCase.editStudent(id, startEducation, name)
        }
    }

    fun getAllMembers() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                schoolUseCase.getAllMembers()
            }.collect {
                when (it.state) {
                    is SuccessState -> {
                        _viewState.value = _viewState.value?.copy(
                            loading = false,
                            success = true,
                            error = false,
                            entity = it.entity
                        )

                    }
                    is LoadingState -> {
                        _viewState.value = _viewState.value?.copy(
                            loading = true,
                            success = false,
                            error = false,
                            entity = null
                        )
                    }
                    is ErrorState -> {
                        _viewState.value = _viewState.value?.copy(
                            loading = false,
                            success = false,
                            error = true,
                            entity = null
                        )
                    }
                }

            }

        }


    }


}


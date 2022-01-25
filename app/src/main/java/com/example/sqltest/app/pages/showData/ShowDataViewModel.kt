package com.example.sqltest.app.pages.showData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqltest.base.state.ErrorState
import com.example.sqltest.base.state.LoadingState
import com.example.sqltest.base.state.SuccessState
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.CourseUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.SchoolUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.StudentUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.TeacherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowDataViewModel(
    val courseUseCase: CourseUseCase,
    var teacherUseCase: TeacherUseCase,
    var studentUseCase: StudentUseCase,
    var schoolUseCase: SchoolUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ShowViewState>()
    val viewState: LiveData<ShowViewState> = _viewState

    init {
        _viewState.value =
            ShowViewState(
                loading = false,
                success = false,
                error = false,
                entity = null
            )

    }
    fun deleteCourse(id: Int) {
        viewModelScope.launch {
            courseUseCase.deleteCourseById(id)
        }
    }
    fun deleteTeacher(id: Int) {
        viewModelScope.launch {
            teacherUseCase.deleteTeacherById(id)
        }
    }
    fun deleteStudent(id: Int) {
        viewModelScope.launch {
            studentUseCase.deleteStudentById(id)
        }
    }
    fun deleteStudentFromSchool(id: Int) {
        viewModelScope.launch {
            schoolUseCase.deleteStudentFromSchool(id)
        }
    }

    fun getCourses(isSorted: Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                courseUseCase.getCourses(isSorted)
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

    fun getTeachers(onlyLaureate: Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                teacherUseCase.getTeachers(onlyLaureate)
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

    fun getStudent() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                studentUseCase.getStudents()
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

    fun getSchool() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                schoolUseCase.getMusicSchool()
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
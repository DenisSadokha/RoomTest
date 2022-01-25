package com.example.sqltest.app.pages.showData

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqltest.R
import com.example.sqltest.app.pages.insertData.InsertActivity
import com.example.sqltest.app.pages.start.MainActivity
import com.example.sqltest.app.utils.appComponent
import com.example.sqltest.base.present.viewModelFactory
import com.example.sqltest.databinding.ShowdataActivityBinding
import com.example.sqltest.domain.features.musicSchoolInfo.entities.*
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.CourseUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.SchoolUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.StudentUseCase
import com.example.sqltest.domain.features.musicSchoolInfo.use_cases.TeacherUseCase
import javax.inject.Inject

class ShowInfoActivity : AppCompatActivity() {

    lateinit var binding: ShowdataActivityBinding


    @Inject
    lateinit var courseUseCase: CourseUseCase

    @Inject
    lateinit var teacherUseCase: TeacherUseCase

    @Inject
    lateinit var studentUseCase: StudentUseCase

    @Inject
    lateinit var schoolUseCase: SchoolUseCase

    lateinit var showAdapter: ShowDataAdapter


    private val viewModel: ShowDataViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory {
                ShowDataViewModel(
                    courseUseCase,
                    teacherUseCase,
                    studentUseCase,
                    schoolUseCase
                )
            }
        )[ShowDataViewModel::class.java]
    }


    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        val bundle = intent.extras
        val entityCode = bundle?.getInt(MainActivity.CODE_OF_TABLE) ?: -1
        binding = DataBindingUtil.setContentView(this, R.layout.showdata_activity)


//        initDepend()

        showView(entityCode)

        initRecycler(entityCode)

        getData(entityCode)

        binding.btnFloatAdd.setOnClickListener {
            handlingClickFloat(it, entityCode)
        }



        viewModel.viewState.observe(this, { handleState(it, entityCode) })


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
                binding.switchTeacherIsLaureate.setOnCheckedChangeListener { compoundButton, b ->
                    viewModel.getTeachers(b)
                }
            }
            MainActivity.COURSE -> {
                binding.viewGroupCourse.visibility = View.VISIBLE
                binding.switchCourseSortByName.setOnCheckedChangeListener { compoundButton, b ->
                    viewModel.getCourses(b)
                }

            }
        }
    }


    private fun handlingClickFloat(view: View, entityCode: Int) {
        val intent = Intent(this, InsertActivity::class.java)
        intent.putExtra(MainActivity.CODE_OF_TABLE, entityCode)
        startActivityForResult(intent, 0)
    }

    private fun getData(entityCode: Int) {
        when (entityCode) {
            MainActivity.SCHOOL -> {
                viewModel.getSchool()
            }
            MainActivity.STUDENT -> {
                viewModel.getStudent()
            }
            MainActivity.TEACHER -> {
                viewModel.getTeachers(binding.switchTeacherIsLaureate.isChecked)
            }
            MainActivity.COURSE -> {
                viewModel.getCourses(binding.switchCourseSortByName.isChecked)

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val entityCode = data.extras?.getInt(MainActivity.CODE_OF_TABLE) ?: -1
            getData(entityCode)

        }


    }

    private fun initRecycler(entityCode: Int) {
        showAdapter = ShowDataAdapter(mutableListOf())


        showAdapter.setOnItemClickListener { entity, pos ->

            val intent = Intent(this, InsertActivity::class.java)
            when (entityCode) {
                MainActivity.COURSE -> {
                    intent.putExtra("name", (entity as Course).name)
                    intent.putExtra("id", (entity as Course).id)
                    intent.putExtra("duration", (entity as Course).courseDuration)
                    intent.putExtra("isEdit", 1)

                    intent.putExtra(MainActivity.CODE_OF_TABLE, entityCode)
                    startActivityForResult(intent, 0)


                }
                MainActivity.TEACHER -> {
                    intent.putExtra("name", (entity as Teacher).name)
                    intent.putExtra("id", entity.id)
                    intent.putExtra("laureate", entity.laureateDegree)
                    intent.putExtra("isEdit", 1)

                    intent.putExtra(MainActivity.CODE_OF_TABLE, entityCode)
                    startActivityForResult(intent, 0)

                }
                MainActivity.STUDENT -> {
                    intent.putExtra("name", (entity as Student).name)
                    intent.putExtra("id", entity.id)
                    intent.putExtra("startEducation", entity.startEducation)
                    intent.putExtra("isEdit", 1)
                    intent.putExtra(MainActivity.CODE_OF_TABLE, entityCode)
                    startActivityForResult(intent, 0)

                }
                MainActivity.SCHOOL -> {
                }
            }


        }

        showAdapter.setOnLongItemClickListener { entity, pos ->


            when (entityCode) {
                MainActivity.COURSE -> {
                    try {
                        viewModel.deleteCourse(entity.id)
                        showAdapter.removeItem(pos)

                    } catch (e: Exception) {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                    }
                }
                MainActivity.TEACHER -> {
                    viewModel.deleteTeacher(entity.id)
                    showAdapter.removeItem(pos)
                }
                MainActivity.STUDENT -> {
                    viewModel.deleteStudent(entity.id)
                    showAdapter.removeItem(pos)
                }
                MainActivity.SCHOOL -> {
                    try {
                        viewModel.deleteStudentFromSchool(entity.id)
                        showAdapter.removeItem(pos)

                    } catch (e: Exception) {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                    }
                }

            }


        }


        binding.rvDataInfo.apply {
            adapter = showAdapter
            layoutManager =
                LinearLayoutManager(this@ShowInfoActivity)
            setHasFixedSize(true)
            setItemViewCacheSize(20)

        }
    }

    private fun fillList(list: List<String>) {
        Log.d("ListItem", list.size.toString())
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        binding.rvNames.adapter = adapter


    }


    private fun handleState(state: ShowViewState, entityCode: Int) {
        when {
            state.loading -> {


            }
            state.success -> {
                when (entityCode) {
                    MainActivity.COURSE -> {
                        showAdapter.setNewData((state.entity as CourseEntity).courses)

                    }
                    MainActivity.TEACHER -> {
                        showAdapter.setNewData((state.entity as TeacherEntity).teachers)


                    }
                    MainActivity.STUDENT -> {
                        showAdapter.setNewData((state.entity as StudentEntity).students)
                        fillList(state.entity.studentAndTeacherNames)
                    }
                    MainActivity.SCHOOL -> {
                        showAdapter.setNewData((state.entity as MusicSchoolEntity).list)
                        binding.tvSchoolCount.text = state.entity.count.toString()
                        Log.d("Schoool", " suc" + state.entity.joins)

                    }

                }

                Log.d("Stateeees", " suc" + state.entity)

            }
            state.error -> {

            }
        }

    }


}


package com.example.sqltest.app.pages.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sqltest.R
import com.example.sqltest.app.pages.showData.ShowInfoActivity
import com.example.sqltest.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding

    companion object {
        const val CODE_OF_TABLE = "tableCode"
        const val TEACHER = 0
        const val STUDENT = 1
        const val COURSE = 2
        const val SCHOOL = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        binding.btnCourses.setOnClickListener {
            handleButtonClick(it.id)
        }
        binding.btnTeacher.setOnClickListener {
            handleButtonClick(it.id)
        }
        binding.btnSchool.setOnClickListener {
            handleButtonClick(it.id)
        }
        binding.btnStudents.setOnClickListener {
            handleButtonClick(it.id)
        }


    }

    fun handleButtonClick(id: Int) {
        val intent = Intent(this, ShowInfoActivity::class.java)
        when(id) {
            R.id.btnCourses -> {
                intent.putExtra(CODE_OF_TABLE, COURSE)
                startActivity(intent)
            }
            R.id.btnSchool -> {
                intent.putExtra(CODE_OF_TABLE, SCHOOL)
                startActivity(intent)
            }
            R.id.btnStudents -> {
                intent.putExtra(CODE_OF_TABLE, STUDENT)
                startActivity(intent)
            }
            R.id.btnTeacher -> {
                intent.putExtra(CODE_OF_TABLE, TEACHER)
                startActivity(intent)
            }

        }

    }
}
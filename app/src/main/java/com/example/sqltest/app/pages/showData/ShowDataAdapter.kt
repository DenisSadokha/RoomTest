package com.example.sqltest.app.pages.showData

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sqltest.base.entity.EntityModel
import com.example.sqltest.databinding.CourseItemBinding
import com.example.sqltest.databinding.MusicSchoolItemBinding
import com.example.sqltest.databinding.StudentItemBinding
import com.example.sqltest.databinding.TeacherItemBinding
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Course
import com.example.sqltest.domain.features.musicSchoolInfo.entities.MusicsSchoolStudent
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Student
import com.example.sqltest.domain.features.musicSchoolInfo.entities.Teacher

class ShowDataAdapter(
    val data: MutableList<EntityModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val COURSE_TYPE = 0
        const val STUDENT_TYPE = 1
        const val MUSICSCHOOL_TYPE = 2
        const val TEACHER_TYPE = 3
        const val UNKNOWN_TYPE = 4
    }

    private var mClickLongListener: ((EntityModel, Int) -> Unit)? = null

    private var mClickListener: ((EntityModel, Int) -> Unit)? = null

    fun setOnLongItemClickListener(listener: (EntityModel, Int) -> Unit) {
        mClickLongListener = listener
    }

    fun setOnItemClickListener(listener: (EntityModel, Int) -> Unit) {
        mClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            COURSE_TYPE -> {
                return CourseViewHolder(
                    CourseItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )


            }
            TEACHER_TYPE -> {
                return TeacherViewHolder(
                    TeacherItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            STUDENT_TYPE -> {
                return StudentViewHolder(
                    StudentItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            MUSICSCHOOL_TYPE -> {
                return MusicSchoolViewHolder(
                    MusicSchoolItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            else -> {
                throw Exception()
            }


        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("viewHiodler", " qweqw")

        return when (data[position]) {

            is Course -> {
                COURSE_TYPE
            }
            is Teacher -> {
                TEACHER_TYPE
            }
            is Student -> {
                STUDENT_TYPE
            }

            is MusicsSchoolStudent -> {
                MUSICSCHOOL_TYPE
            }
            else -> {
                UNKNOWN_TYPE
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            COURSE_TYPE -> {
                (holder as CourseViewHolder).bind(data[position] as Course, position)
            }
            TEACHER_TYPE -> {
                (holder as TeacherViewHolder).bind(data[position] as Teacher, position)
            }
            STUDENT_TYPE -> {
                (holder as StudentViewHolder).bind(data[position] as Student, position)
            }
            MUSICSCHOOL_TYPE -> {
                (holder as MusicSchoolViewHolder).bind(
                    data[position] as MusicsSchoolStudent,
                    position
                )
            }
        }
    }


    inner class CourseViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            mClickLongListener.let { listener ->
                itemView.setOnLongClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnLongClickListener true
                }
            }

            mClickListener.let { listener ->
                itemView.setOnClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnClickListener
                }
            }

        }

        fun bind(item: Course, pos: Int) {
            Log.d("viewHiodler", " qweqw")
            binding.setVariable(1, item)
            if (binding is CourseItemBinding) {
                binding.tvName.text = item.name
                binding.tvEducate.text = item.courseDuration
            }

        }
    }

    inner class TeacherViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            mClickLongListener.let { listener ->
                itemView.setOnLongClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnLongClickListener true
                }
            }

            mClickListener.let { listener ->
                itemView.setOnClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnClickListener
                }
            }

        }
        fun bind(item: Teacher, pos: Int) {
            binding.setVariable(1, item)
            if (binding is TeacherItemBinding) {
                binding.tvName.text = item.name
                binding.tvLaureate.text = if (item.laureateDegree) "Этот препод лауреат"
                else "Этот препод не лауреат"
            }

        }
    }

    inner class StudentViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            mClickLongListener.let { listener ->
                itemView.setOnLongClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnLongClickListener true
                }
            }

            mClickListener.let { listener ->
                itemView.setOnClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnClickListener
                }
            }

        }
        fun bind(item: Student, pos: Int) {
            binding.setVariable(1, item)
            if (binding is StudentItemBinding) {
                binding.tvName.text = item.name
                binding.tvStartEducation.text = item.startEducation
            }

        }
    }

    inner class MusicSchoolViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            mClickLongListener.let { listener ->
                itemView.setOnLongClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnLongClickListener true
                }
            }

            mClickListener.let { listener ->
                itemView.setOnClickListener {
                    listener?.invoke(data[adapterPosition], adapterPosition)
                    return@setOnClickListener
                }
            }

        }
        fun bind(item: MusicsSchoolStudent, pos: Int) {
            binding.setVariable(1, item)
            if (binding is MusicSchoolItemBinding) {
                binding.tvCourse.text = item.course.name
                binding.tvStudent.text = item.student.name
                binding.tvTeacher.text = item.teacher.name
            }

        }
    }

    fun setNewData(newData: List<EntityModel>) {
        data.clear()
        data.addAll(newData)

        notifyDataSetChanged()
    }
    fun removeItem(pos: Int) {
        data.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, data.size)

    }


    override fun getItemCount(): Int = data.size
}
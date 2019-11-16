package com.example.studentroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view.view.*
import java.util.logging.Handler

class StudentAdapter(var context: Context): RecyclerView.Adapter<StudentAdapter.StudentHolder>(){


    var students = emptyList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false)
        return StudentHolder(view);
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
       holder.bindItems(students.get(position))
    }

    fun setStudent(students: List<Student>){
        this.students = students
        notifyDataSetChanged()
    }

    inner class StudentHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //val name = view.name
        //val age = view.age
        fun bindItems(student: Student){
            val name = itemView.name
            val age = itemView.age
            name.text = student.name
            age.text = student.age
            itemView.setOnClickListener{

            }
        }
    }
}
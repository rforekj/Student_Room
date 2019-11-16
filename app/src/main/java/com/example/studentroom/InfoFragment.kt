package com.example.studentroom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.info_student.view.*

class InfoFragment: Fragment(), OnDataPass {

    lateinit var dataStudent: OnDataPass
    override fun onDataPass(student: Student) {
        dataStudent.onDataPass(student)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataStudent = context as OnDataPass
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.info_student, container, false)

        view.name_info.setText(arguments?.getString("name"))
        view.age_info.setText(arguments?.getString("age"))

        view.edit_button.setOnClickListener{
            val student = Student(view.name_info.text.toString(),view.age_info.text.toString())
            dataStudent.onDataPass(student)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        view.delete_button.setOnClickListener{
            val student = Student("251099","10")
            dataStudent.onDataPass(student)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        return view
    }

}
public interface OnDataPass{
    public fun onDataPass(student: Student)
}
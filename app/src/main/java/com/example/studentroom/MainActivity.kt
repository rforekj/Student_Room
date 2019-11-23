package com.example.studentroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnDataPass {

    private lateinit var studentViewModel: StudentViewModel
    private val newStudentcode = 1
    private lateinit var name_key:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = rv_student
        var adapter = StudentAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                var fragment = InfoFragment()
                var name = studentViewModel.allStudent.value!![position].name
                var age = studentViewModel.allStudent.value!![position].age
                name_key = name
                var bundle = Bundle()
                bundle.putString("name", name)
                bundle.putString("age", age)
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.left_to_right,R.anim.right_to_left)
                    .replace(R.id.frame_layout, fragment).commit()
            }
        })


        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        studentViewModel.allStudent.observe(this, Observer {
            students -> students?.let {adapter.setStudent(students)}
        })

        val addButton = addbutton
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, newStudentcode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newStudentcode && resultCode == Activity.RESULT_OK ) {
            val bundle = data?.getExtras()
            if(bundle!=null){
                val newStudent = Student(bundle.getString("name",""),
                                         bundle.getString("age",""))
                Log.i("truoc",bundle.getString("name",""))
                studentViewModel.insert(newStudent)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }

    override fun onDataPass(student: Student) {
        if(student.name == "251099") studentViewModel.delete(name_key)
        else studentViewModel.update(student.name,student.age,name_key)
    }
}

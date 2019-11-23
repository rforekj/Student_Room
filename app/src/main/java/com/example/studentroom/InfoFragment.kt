package com.example.studentroom

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.card_view.*
import kotlinx.android.synthetic.main.info_student.view.*

class InfoFragment: Fragment(), OnDataPass {

    val PICK_IMAGE = 1

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


        view.avatar.setOnClickListener{
            pickImage()
        }


        view.name_info.setText(arguments?.getString("name"))
        view.age_info.setText(arguments?.getString("age"))

        view.edit_button.setOnClickListener{
            val student = Student(view.name_info.text.toString(),view.age_info.text.toString())
            dataStudent.onDataPass(student)
            activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_open_exit)
                ?.remove(this)?.commit()
        }

        view.delete_button.setOnClickListener{
            val student = Student("251099","10")
            dataStudent.onDataPass(student)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        return view
    }

    public fun pickImage(){
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(intent,PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode == PICK_IMAGE){
                val extras  = data?.data
                if(extras != null){
                    view?.avatar?.setImageURI(extras)
                }
            }
    }
}
public interface OnDataPass{
    public fun onDataPass(student: Student)
}
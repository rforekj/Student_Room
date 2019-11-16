package com.example.studentroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        val addbut: Button = add2
        addbut.setOnClickListener{
            bundle.putString("name",addname.text.toString())
            bundle.putString("age",addage.text.toString())
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}

package com.example.studentroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import java.lang.NumberFormatException

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        val addbut: Button = add2
        addbut.setOnClickListener{
            try {
                if(addname.text.toString()==""||addage.text.toString()=="") throw Exception()
                var ageInt = Integer.parseInt(addage.text.toString())
                if(ageInt<0) throw NumberFormatException()

                bundle.putString("name", addname.text.toString())
                bundle.putString("age", addage.text.toString())
                intent.putExtras(bundle)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } catch (e:NumberFormatException) {
                Toast.makeText(getApplicationContext(),
                    "age must be number and greater than 0",Toast.LENGTH_SHORT).show()
            } catch (e:java.lang.Exception){
                Toast.makeText(getApplicationContext(),
                    "name and age can not be null",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

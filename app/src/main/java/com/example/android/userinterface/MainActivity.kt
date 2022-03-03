package com.example.android.userinterface

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.core.view.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var m_txtVw:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Accessing the "strings.xml" values file for the "dummylbl" string value.
        addTextview(getString(R.string.dummylbl))

        btn1.setOnClickListener{
            m_txtVw!!.visibility = if(m_txtVw!!.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
        }

        btn2.setOnClickListener{
            Snackbar.make(it, getString(R.string.infovalue, 51,"Jeff"), Snackbar.LENGTH_LONG).show()
        }

        //Just showing how to set the image in an image view programatically
        img1.setImageResource(R.drawable.ic_launcher_background)
    }

    private fun addTextview(lbl:String) {
        if(m_txtVw != null) {
            return
        }

        //Here - We're still getting just a string resource, BUT we are using a
        //var-args to fillin the formatted string as set by the rules in the
        //strings.xml file.
        val newLbl:String = getString(R.string.infovalue, 51,"Jeff")

        m_txtVw = TextView(this)
        m_txtVw!!.id = ViewCompat.generateViewId() //EVERY View in the constraint view MUST have a ID assigned.
        m_txtVw!!.text = lbl + " " + newLbl

        m_txtVw!!.textSize = 28f //28dp
        m_txtVw!!.setTextColor(Color.parseColor("#C2FFC2"))

        parentLayout.addView(m_txtVw)

        val constraints = ConstraintSet()
        constraints.clone(parentLayout) //The element won't display without this.
        constraints.connect(m_txtVw!!.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
        constraints.connect(m_txtVw!!.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraints.applyTo(parentLayout)
    }

}

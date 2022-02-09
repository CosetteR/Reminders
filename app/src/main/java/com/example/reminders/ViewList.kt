package com.example.reminders

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.SparseBooleanArray
import android.view.View
import android.widget.*

class ViewList : AppCompatActivity() {

    lateinit var listOfItems : ListView
    lateinit var listTitle : TextView
    var i = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list)
        listOfItems = findViewById(R.id.listOfItems)
        listTitle = findViewById(R.id.listTitle)

        i = intent.getIntExtra("What", -1)

        listTitle.setText(listsList[i].name)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_checked, listsList[i].list)
        listOfItems.adapter = arrayAdapter

        listOfItems.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val sparseBooleanArray: SparseBooleanArray = listOfItems.getCheckedItemPositions()
            Toast.makeText(applicationContext, "Clicked Position := " + i.toString() + " Value: " + sparseBooleanArray[i], Toast.LENGTH_LONG).show()
            //need to add code that just deletes it
        }
    }

    fun addItem(view : View?){ //need to attribute https://handyopinion.com/show-alert-dialog-with-an-input-field-edittext-in-android-kotlin/?fbclid=IwAR3q1mhkmfbF8HY_Do5J_WjCmgYW39_bQuBeifQpv5146Yt8aKKHpqi1vEw
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Title")

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Enter Text")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var item = input.text.toString()
            listsList[i].list.add(item)

            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listsList[i].list)
            listOfItems.adapter = arrayAdapter
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}
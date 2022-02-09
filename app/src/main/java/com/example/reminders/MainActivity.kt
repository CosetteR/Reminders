package com.example.reminders

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener


var listsList = ArrayList<List>()
class MainActivity : AppCompatActivity() {
    lateinit var myList: TextView
    lateinit var lists: ListView
    lateinit var add: Button
    var listOfLists = ArrayList<String>()//what's currently displayed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lists = findViewById(R.id.lists)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfLists)
        lists.adapter = arrayAdapter

        lists.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, ViewList::class.java)
            intent.putExtra("What", i)
            startActivity(intent)

        }//lists.onItemClickListener

        lists.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this list from your lists?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener(){
                        dialogInterface: DialogInterface?, j: Int ->
                    listsList.removeAt(i)
                    listOfLists.removeAt(i)
                    val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfLists)
                    lists.adapter = arrayAdapter
                })
                .setNegativeButton("No",null)
                .show()
            
            true
        }//lists.onItemLongClickListener

    }//onCreate

    fun addList(view : View?){ //need to attribute https://handyopinion.com/show-alert-dialog-with-an-input-field-edittext-in-android-kotlin/?fbclid=IwAR3q1mhkmfbF8HY_Do5J_WjCmgYW39_bQuBeifQpv5146Yt8aKKHpqi1vEw
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
            var title = input.text.toString()
            var emptyList = ArrayList<String>()
            var titleList = List(title, emptyList)
            listsList.add(titleList)
            listOfLists.add(title)

            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfLists)
            lists.adapter = arrayAdapter
            
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}//MainActivity
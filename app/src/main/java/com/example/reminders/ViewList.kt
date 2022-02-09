package com.example.reminders

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*

class ViewList : AppCompatActivity() {

    var dataModel: ArrayList<DataModel>? = null
    lateinit var cAdapter: CustomAdapter
    lateinit var listOfItems : ListView
    lateinit var listTitle : TextView
    var i = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list)
        listOfItems = findViewById<View>(R.id.listOfItems) as ListView
        listTitle = findViewById(R.id.listTitle)

        i = intent.getIntExtra("What", -1)

        listTitle.text = listsList[i].name

        dataModel = listsList[i].list
        cAdapter = CustomAdapter(dataModel!!, applicationContext)
        listOfItems.adapter = cAdapter

        listOfItems.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, l ->
            val dataModel: DataModel = dataModel!![position] as DataModel
            dataModel.checked = !dataModel.checked
            listsList[i].list[position].checked = dataModel.checked
            cAdapter.notifyDataSetChanged()
        }//listOfItems.onItemClickListener

        listOfItems.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, loc, l ->

            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item from your list?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener(){
                        dialogInterface: DialogInterface?, j: Int ->
                    listsList[i].list!!.removeAt(loc)
                    dataModel = listsList[i].list
                    cAdapter = CustomAdapter(dataModel!!, applicationContext)
                    listOfItems.adapter = cAdapter
                })
                .setNegativeButton("No",null)
                .show()

            true
        }//listOfItems.onItemLongClickListener
    }//onCreate

    fun addItem(view : View?){
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("New Item")

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Description")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var item = input.text.toString()
            listsList[i].list!!.add(DataModel(item,false))
            dataModel = listsList[i].list

            cAdapter = CustomAdapter(dataModel!!, applicationContext)
            listOfItems.adapter = cAdapter
        })//builder.setPositiveButton
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }//addItem
}//ViewList
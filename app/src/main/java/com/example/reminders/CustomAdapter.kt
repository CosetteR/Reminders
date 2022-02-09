package com.example.reminders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import java.util.*

class CustomAdapter(private val dataSet: ArrayList<*>, mContext: Context) :

    ArrayAdapter<Any?>(mContext, R.layout.row_item, dataSet) {
        private class ViewHolder {
            lateinit var txtName: TextView
            lateinit var checkBox: CheckBox
        }//ViewHolder
        override fun getCount(): Int {
            return dataSet.size
        }//getCount
        override fun getItem(position: Int): DataModel {
            return dataSet[position] as DataModel
        }//getItem
        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            var convertView = convertView
            val viewHolder: ViewHolder
            val result: View
            if (convertView == null) {
                viewHolder = ViewHolder()
                convertView =
                    LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
                viewHolder.txtName =
                    convertView.findViewById(R.id.txtName)
                viewHolder.checkBox =
                    convertView.findViewById(R.id.checkBox)
                result = convertView
                convertView.tag = viewHolder
            } //if
            else {
                viewHolder = convertView.tag as ViewHolder
                result = convertView
            } //else
            val item: DataModel = getItem(position)
            viewHolder.txtName.text = item.name
            viewHolder.checkBox.isChecked = item.checked
            return result
        }//View
}//ArrayAdapter
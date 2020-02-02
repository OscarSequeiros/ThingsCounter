package com.osequeiros.thingscounter.presentation.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.osequeiros.thingscounter.R
import com.osequeiros.thingscounter.presentation.model.ItemModel
import kotlinx.android.synthetic.main.dialog_new_item.*

class NewItemDialog : DialogFragment() {

    private var callback: NewItemCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_new_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActions()
    }

    override fun onStart() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val color = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(color, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        super.onStart()
    }

    private fun setUpActions() {
        buttonAddItem.setOnClickListener {
            val item = ItemModel(title = editNewItem.text.toString())
            callback?.addItem(item)
            dismiss()
        }
    }

    companion object {

        fun instance(newItemCallback: NewItemCallback): NewItemDialog {
            return NewItemDialog().also {
                it.callback = newItemCallback
            }
        }
    }
}
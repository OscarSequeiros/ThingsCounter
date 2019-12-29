package com.osequeiros.thingscounter.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.osequeiros.thingscounter.R
import com.osequeiros.thingscounter.presentation.counter.model.ItemModel
import com.osequeiros.thingscounter.presentation.counter.view.NewItemCallback
import kotlinx.android.synthetic.main.bottom_sheet_new_item.*

class NewItemBottomSheetDialog : BottomSheetDialogFragment() {

    private var callback: NewItemCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_new_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActions()
    }

    private fun setUpActions() {
        buttonAddItem.setOnClickListener {
            val item = ItemModel(title = editNewItem.text.toString())
            callback?.addItem(item)
            dismiss()
        }
    }

    companion object {

        fun instance(newItemCallback: NewItemCallback): NewItemBottomSheetDialog {
            return NewItemBottomSheetDialog().also {
                it.callback = newItemCallback
            }
        }
    }
}
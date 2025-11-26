package com.rose.instagram.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.rose.instagram.R
import com.rose.instagram.databinding.DialogCustomBinding
import com.rose.instagram.databinding.FragmentRegisterPhotoBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding
    private lateinit var txtButtons: Array<TextView>

    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dialogContainer
        binding.dialogTitle
    }

    fun addButton(vararg texts: Int, listiner: View.OnClickListener, ){
        txtButtons = Array(texts.size){
            TextView(context)
        }

        texts.forEachIndexed{index, textId ->
            txtButtons[index].setText(textId)
            txtButtons[index].setOnClickListener{
                listiner.onClick(it)
                dismiss()
            }
        }
    }

    override fun show() {
        super.show()

        titleId?.let{
            binding.dialogTitle.setText(it)
        }

        for(textView in txtButtons){
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30,30,30,30)

            binding.dialogContainer.addView(textView, layoutParams)
        }
    }
}
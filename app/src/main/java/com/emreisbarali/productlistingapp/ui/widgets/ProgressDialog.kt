package com.emreisbarali.productlistingapp.ui.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.emreisbarali.productlistingapp.R

class ProgressDialog(context:Context) : Dialog(context) {

    init {
        window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setContentView(R.layout.progress_dialog)
        setCancelable(false)
    }
}
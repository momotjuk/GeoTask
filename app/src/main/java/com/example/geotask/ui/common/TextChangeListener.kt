package com.example.geotask.ui.common

import android.text.Editable
import android.text.TextWatcher

class TextChangeListener(val changed: (TextChangeListener) -> Unit) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        // skip
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // skip
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        changed(this)
    }
}
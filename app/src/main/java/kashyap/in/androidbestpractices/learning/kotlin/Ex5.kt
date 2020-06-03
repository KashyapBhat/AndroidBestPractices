package kashyap.`in`.androidbestpractices.learning.kotlin

import android.app.Activity
import android.app.Dialog
import android.os.Bundle

class Example5Activity(dialog: Dialog) : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullHandling()
    }

    private fun nullHandling() {
        var number: String? = null
        funtionsOnNullObjects(number)
        option1(number)
        option2(number)
        textUtilsEmptyChecker(number)
    }

    private fun textUtilsEmptyChecker(number: String?) {
        if (!number.isNullOrEmpty()) {
//            do something
        }
    }

    private fun funtionsOnNullObjects(number: String?) {
        number?.length
        number?.length ?: -1
        number!!.length
    }

    private fun option1(number: String?) {
//        if (number != null) {
//            text?.text = number
//        }

        number?.let {
            it.plus(1)
            it
        }
        number?.apply {
            plus(1)
        }
    }

    private fun option2(number: String?) {
        val text = number ?: "1"
    }

}
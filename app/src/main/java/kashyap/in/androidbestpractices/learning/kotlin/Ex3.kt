package kashyap.`in`.androidbestpractices.learning.kotlin

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class ExampleActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        synthetics()
        setListener()
        retryUploadDialog(this)
    }

    private fun synthetics() {
//        import kotlinx.android.synthetic.main.activity_main.llBody
//        llMain?.visibility = View.GONE
    }

    // Example this project main activity

    private fun setListener() {
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                 do something
//            }
//        });

//        btNext.setOnClickListener { }
    }

    private fun retryUploadDialog(context: Context) {

    }


    override fun onClick(v: View?) {

    }
}
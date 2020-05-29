package kashyap.`in`.androidbestpractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kashyap.`in`.androidbestpractices.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

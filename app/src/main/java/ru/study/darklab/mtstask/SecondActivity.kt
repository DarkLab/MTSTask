package ru.study.darklab.mtstask

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)

    supportActionBar?.run {
      setDisplayHomeAsUpEnabled(true)
      setHomeButtonEnabled(true)
    }

    val message = intent.action?.takeIf { it == Intent.ACTION_VIEW }?.let {
      intent.dataString?.run {
        substringAfter("second").takeIf { it.length > 1 }?.let { result ->
          "${getString(R.string.show_data)}\n${result.substring(1)}"
        }
      } ?: getString(R.string.followed_the_link)
    } ?: intent.extras?.getString(TRANSFER_DATA_STRING, getString(R.string.no_data))
    findViewById<TextView>(R.id.received_data_tv)?.text = message
  }

  companion object {
    const val TRANSFER_DATA_STRING = "transfer_data_string"
  }
}

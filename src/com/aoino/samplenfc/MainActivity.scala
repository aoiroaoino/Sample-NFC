
package com.aoino.samplenfc

import android.os.Bundle
import android.app.Activity
import android.view.Menu
import android.content.Intent
import android.nfc.NfcAdapter
import android.widget.TextView

class MainActivity extends Activity {
  
  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    val intent: Intent = getIntent
    val action: String = intent.getAction
    
    if (action.equals(NfcAdapter.ACTION_TECH_DISCOVERED)) {
      val rawId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
      val text = byte2Text(rawId)
      val nfcIdTextView = findViewById(R.id.nfc_id_textview).asInstanceOf[TextView]
      nfcIdTextView.setText(text)
    }
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu)
    true
  }

  private def byte2Text(bytes: Array[Byte]): String = {
    val buffer = new StringBuilder()
    bytes.foreach { (b: Byte) =>
      val hex = format("%02X", b.asInstanceOf[AnyRef])
      buffer.append(hex).append(" ")
    }
    buffer.toString.trim
  }

}
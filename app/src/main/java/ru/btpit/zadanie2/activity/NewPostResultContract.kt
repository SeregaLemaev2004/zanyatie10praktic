package ru.btpit.zadanie2.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import ru.btpit.zadanie2.R

class NewPostResultContract : ActivityResultContract<String, String?>() {

    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, NewPostActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)

        } else {
            null
        }
}
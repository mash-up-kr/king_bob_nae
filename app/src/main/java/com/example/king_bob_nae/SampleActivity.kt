package com.example.king_bob_nae

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.king_bob_nae.base.viewDataBinding
import com.example.king_bob_nae.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {

    private val binding by viewDataBinding<ActivitySampleBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        binding.btnHelloWorld.setOnClickListener {
            supportFragmentManager.commit {
                replace(android.R.id.content, SampleFragment())
            }
        }
    }

    companion object {
        fun obtain(context: Context): Intent {
            return Intent(context, SampleActivity::class.java)
        }
    }
}

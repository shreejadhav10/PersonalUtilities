package com.shree.personalutilities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initToolbar();
    }

    private fun initToolbar() =
        findViewById<Toolbar>(R.id.toolbarMainActivity).also {
            setSupportActionBar(it);
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }.apply {
            navigationIcon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    ContextCompat.getColor(this@BaseActivity, R.color.white),
                    BlendModeCompat.SRC_ATOP
                )
            setNavigationOnClickListener {
                finish()
            }
        }

}


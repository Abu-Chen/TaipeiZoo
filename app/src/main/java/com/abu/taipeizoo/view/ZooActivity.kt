package com.abu.taipeizoo.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import com.abu.taipeizoo.R
import com.abu.taipeizoo.extension.TAG
import com.abu.taipeizoo.model.Area

class ZooActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        host.navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(this@ZooActivity, "Navigated to $dest",
                Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Navigated to $dest")
        }
    }
}
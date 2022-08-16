package com.alisamir.newsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alisamir.newsapp.databinding.ActivityMainBinding
import com.alisamir.newsapp.ui.ThemeLiveData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var controller:NavController
    lateinit var appBar:AppBarConfiguration
    lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.modeItem){
            bottomSheetDialog.show()
            return true
        }else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate: ")
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var theme = ThemeLiveData()
        bottomSheetDialog = BottomSheetDialog(this)
        val darkModePrefs = getSharedPreferences("Mode", MODE_PRIVATE)
        val darkSheetView = layoutInflater.inflate(R.layout.theme_bottom_sheet_layout,null)
        bottomSheetDialog.setContentView(darkSheetView)
        val themeSwitch = darkSheetView.findViewById<SwitchMaterial>(R.id.modeSwitch)
        themeSwitch.setOnCheckedChangeListener{_,isChecked->
            if (isChecked){
                darkModePrefs.edit().putBoolean("dark",true).apply()
                theme.setTheme(isChecked)
            }else{
                darkModePrefs.edit().putBoolean("dark",false).apply()
                theme.setTheme(isChecked)
            }
            bottomSheetDialog.dismiss()
        }
        val isDark = darkModePrefs.getBoolean("dark",false)
        setAppTheme(isDark , themeSwitch)
        theme.observe(this){
            setAppTheme(it , themeSwitch)
        }
       /* val host = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        controller = host.navController
        controller.addOnDestinationChangedListener(object :NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when(destination.id){
                    R.id.homeNews -> {
                        //setSupportActionBar(binding.toolbar)
                    }
                    R.id.descriptionNews -> {
                        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
                    }
                }
            }

        })*/

    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBar)||super.onSupportNavigateUp()
    }
    fun setAppTheme(isDark: Boolean , switch:SwitchMaterial){
        if (isDark){
            switch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            switch.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
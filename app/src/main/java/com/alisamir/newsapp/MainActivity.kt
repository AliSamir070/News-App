package com.alisamir.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alisamir.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var controller:NavController
    lateinit var appBar:AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
}
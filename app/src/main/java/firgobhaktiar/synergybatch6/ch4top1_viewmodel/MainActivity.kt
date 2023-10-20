package firgobhaktiar.synergybatch6.ch4top1_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get nav host fragment from activity
        val navHostFragment =
            supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment
            ) as NavHostFragment

        // get nav controller
        navController = navHostFragment.navController

        // setup action bar
        setupActionBarWithNavController(navController)
    }

    // override onSupportNavigateUp
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
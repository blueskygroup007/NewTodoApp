package com.bluesky.todoapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.bluesky.todoapp.databinding.ActivityMainBinding
import com.bluesky.todoapp.ui.theme.TodoAppTheme

/*AppCompatActivity继承自FragmentActivity,因此才可以用findNavcontroller,
* setupActionBarWithNavController等方法*/
class MainActivity : AppCompatActivity() {

    //    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        //val navController = findNavController(R.id.fragmentContainerView)
        //val navController =binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        //setupActionBarWithNavController(navController)


        /*使用fragmentContainerView作为NavHost时,获取NavController的正确方法*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        //val navController = findNavController(R.id.fragmentContainerView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}






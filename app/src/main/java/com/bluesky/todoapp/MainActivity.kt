package com.bluesky.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bluesky.todoapp.databinding.ActivityMainBinding

/*AppCompatActivity继承自FragmentActivity,因此才可以用findNavcontroller,
* setupActionBarWithNavController等方法*/
class MainActivity : AppCompatActivity() {
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        /*使用fragmentContainerView作为NavHost时,获取NavController的正确方法*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        //setupActionBarWithNavController(navController)
        /*toolbar结合navigation的使用方法
        * 1.选用NoActionBar主题.
        * 2.布局中添加ToolBar,可自定义ToolBar内容
        * 3.使用 Toolbar 时，Navigation 组件会自动处理导航按钮的点击事件，因此您无需替换 onSupportNavigateUp()
        * */
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(
            navController,
            appBarConfiguration
        )
    }

//使用 Toolbar 时，Navigation 组件会自动处理导航按钮的点击事件，因此您无需替换 onSupportNavigateUp()

    /*    override fun onSupportNavigateUp(): Boolean {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            //val navController = findNavController(R.id.fragmentContainerView)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }*/
}






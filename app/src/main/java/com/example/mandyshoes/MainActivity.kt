package com.example.mandyshoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mandyshoes.databinding.ActivityMainBinding
import com.example.mandyshoes.login.viewModel.LoginViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        loginViewModel = LoginViewModel()

        configureDrawer()
        setContentView(binding.root)
    }

    private fun configureDrawer() {
        drawer = binding.drawerLayout
        navView = binding.navigationView

        val toggle = ActionBarDrawerToggle(
            this, drawer, R.string.app_name, R.string.buy_button
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.mipmap.logo_foreground)

        // Drawer click handling
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.about_item -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_instructionsFragment)
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.logout_item -> {
                    loginViewModel.signOut()
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_loginFragment)
                    drawer.closeDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
        }
        configDrawerNavigation()
    }

    private fun configDrawerNavigation(){
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
            .addOnDestinationChangedListener {
                nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestinationId || nd.id == R.id.loginFragment) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.example.campingtoolsandequipment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.facebook.login.Login;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campingtoolsandequipment.databinding.ActivityNavBarBinding;
import com.google.firebase.auth.FirebaseAuth;

import kotlinx.coroutines.Delay;

public class NavBarActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavBarBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth =FirebaseAuth.getInstance();

        binding = ActivityNavBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavBar.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_home,R.id.nav_person, R.id.nav_allCategories, R.id.nav_helpCenter,R.id.nav_card,R.id.nav_order,R.id.nav_wishList,R.id.nav_trending)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_bar);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id=item.getItemId();

        if(item_id==R.id.profile){
            Toast.makeText(this, "This is profile option", Toast.LENGTH_SHORT).show();
        }
        else if(item_id==R.id.download){
            Toast.makeText(this, "This is download option", Toast.LENGTH_SHORT).show();
        }
        else if(item_id==R.id.setting){
            Toast.makeText(this, "This is setting option", Toast.LENGTH_SHORT).show();
        }
        else if(item_id==R.id.exit){
            mAuth.signOut();
            Intent intent = new Intent(NavBarActivity.this,SignInActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Sign Out Successfully!!!", Toast.LENGTH_SHORT).show();
        }
        else if(item_id==R.id.add_card){
            Toast.makeText(this, "This is card option", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_bar);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
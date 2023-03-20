package com.project.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottonNavigationView);

//        toolbar = findViewById(R.id.toolbar);
//
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int id = item.getItemId();
//
//                if (id == R.id.login) {
//                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//                    startActivity(intent);
//
//                } else if (id == R.id.chatbot) {
//                    Toast.makeText(HomeActivity.this, "Chatbot", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });

        //default fragment
        loadFragment(new HomeFragment());

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    loadFragment(new HomeFragment());
                } else if (id == R.id.services) {

                    loadFragment(new ServicesFragment());
                } else if (id == R.id.gmap) {
                    Toast.makeText(HomeActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
//                    loadFragment(new ChatbotFragment());

                    Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                    startActivity(intent);

                } else {
                    loadFragment(new FeedbackFragment());
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.login) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(HomeActivity.this, "Chatbot", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public void loadFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainContainer, fragment);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragInstance = fm.findFragmentById(R.id.mainContainer);

        if (fragInstance instanceof DentalFragment) {
            loadFragment(new ServicesFragment());
        } else if (fragInstance instanceof EyeFragment) {
            loadFragment(new ServicesFragment());
        } else if (fragInstance instanceof ServicesFragment) {
            loadFragment(new HomeFragment());
            bottomNavigation.setSelectedItemId(R.id.home);
        } else if (fragInstance instanceof FeedbackFragment) {
            loadFragment(new HomeFragment());
        } else if (fragInstance instanceof DetailFragment) {
            loadFragment(new ServicesFragment());
        } else {
            finish();
        }
    }
}
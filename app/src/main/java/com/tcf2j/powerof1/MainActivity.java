package com.tcf2j.powerof1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tcf2j.powerof1.Quiz.StartQuizActivity;

public class MainActivity extends AppCompatActivity {
    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makes the toolbar the appbar and selects drawer icon
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);

        //handling when items are clicked in drawer
        drawerLayout= findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        //item keeps highlight when clicked
                        menuItem.setChecked(true);
                        //close drawer when item is selected
                        drawerLayout.closeDrawers();
                        switch(menuItem.getItemId()){
                            case 1:// resources button
                                break;
                            case 2: // Education  button
                                break;
                            case R.id.relationshipQuiz: // Relationship Quiz button
                            startQuiz();
                            break;
                        }




                        return true;
                    }
                }

        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);


    }
    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, StartQuizActivity.class);
        startActivity(intent);
    }

}

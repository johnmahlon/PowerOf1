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
                            case R.id.home: // Home button
                                goHome();
                                break;

                            case R.id.quiz_m: // Relationship Quiz button
                            startQuiz();
                            break;

                            case R.id.resources: // Resources  button
                                break;

                            case R.id.active_m: // Active Bystander  button
                                break;

                            case R.id.consent_m: // Consent  button
                                break;

                            case R.id.dating_m: // Dating Violence  button
                                break;

                            case R.id.sexual_m: // Sexual Assualt  button
                                break;

                            case R.id.stalking_m: // Stalking  button
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
    private void goHome() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, StartQuizActivity.class);
        startActivity(intent);
    }

}

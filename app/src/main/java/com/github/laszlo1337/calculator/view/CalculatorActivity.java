package com.github.laszlo1337.calculator.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.github.laszlo1337.calculator.R;
import com.github.laszlo1337.calculator.presenter.CalculatorPresenter;
import com.github.laszlo1337.calculator.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Presenter.ModeSelectorRelay selectMode;
    @BindView(R.id.drawer_layout)DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CalculatorScreenFragment calculatorScreenFragment = (CalculatorScreenFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_display);
        KeyPadFragment keyPadFragment = (KeyPadFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_input);

        CalculatorPresenter presenter = new CalculatorPresenter(calculatorScreenFragment,keyPadFragment);
        keyPadFragment.setPresenter(presenter);
        selectMode = presenter;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mode_basic) {
            selectMode.selectBasicMode();
        } else if (id == R.id.mode_rpn) {
            selectMode.selectRpnMode();
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

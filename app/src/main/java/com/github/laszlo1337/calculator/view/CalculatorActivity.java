package com.github.laszlo1337.calculator.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.laszlo1337.calculator.R;
import com.github.laszlo1337.calculator.presenter.Calculator;
import com.github.laszlo1337.calculator.presenter.CalculatorPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CalculatorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    private CalculatorPresenter.ModeSelectorRelay selectMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CalculatorDisplayFragment calculatorDisplayFragment = (CalculatorDisplayFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_display);
        CalculatorKeyPadFragment calculatorKeyPadFragment = (CalculatorKeyPadFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_input);

        Calculator presenter = new Calculator(calculatorDisplayFragment, calculatorKeyPadFragment);
        calculatorKeyPadFragment.setCalculatorPresenter(presenter);
        selectMode = presenter;
        selectMode.selectRpnMode();
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

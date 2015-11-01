package com.byan.apaja.main.app.ui.main;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byan.apaja.R;
import com.byan.apaja.main.app.ui.main.mainscreenfragment.MainScreenFragment;
import com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp.MainScreenFragmentView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.appbar_toolbar_init) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.loading_bar) View loadingView;

    private MainScreenFragment mainScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    /* setup drawer navigation */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        final Context context = getBaseContext();
                        final Handler handler = new Handler();
                        switch (menuItem.getItemId()) {
                            case R.id.map_home:
                                showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        hideLoadingBar();
                                    }
                                }, 300L);
                                break;
                            case R.id.train_schedule:
                                showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        hideLoadingBar();
                                    }
                                }, 300L);
                                break;
                            case R.id.all_route:
                                showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        hideLoadingBar();
                                    }
                                }, 300L);
                                break;
                            case R.id.setting_app:
                                showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        hideLoadingBar();
                                    }
                                }, 300L);
                                break;
                            case R.id.about_apaja:
                                showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        hideLoadingBar();
                                    }
                                }, 300L);
                                break;
                        }
                        return true;
                    }
                });
    }

    public MainScreenFragment getMainScreenFragment(){
        return this.mainScreenFragment;
    }

    public void setMainScreenFragment(MainScreenFragment mainScreenFragment){
        this.mainScreenFragment = mainScreenFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_bar:
                /* navigation to another view of fragment */
                getMainScreenFragment().presenter.presentState(MainScreenFragmentView.ViewState.VIEW_SEARCH_STATE);
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showLoadingBar(){
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoadingBar(){
        loadingView.setVisibility(View.GONE);
    }
}

package com.byan.apaja.main.app.ui.main;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byan.apaja.R;
import com.byan.apaja.main.app.ui.main.mainmapfragment.MainMapFragment;
import com.byan.apaja.main.app.ui.main.mvp.MainActivityPresenter;
import com.byan.apaja.main.app.ui.main.mvp.MainActivityPresenterImpl;
import com.byan.apaja.main.app.ui.main.mvp.MainActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
            implements MainActivityView{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.loading_bar) View loadingView;

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        presenter = new MainActivityPresenterImpl(this);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        presenter.presentState(ViewState.INIT);
    }

    private void setupHomeFragment(){
        Fragment fragment = new MainMapFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
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
                                presenter.presentState(ViewState.START_TRANSITION);
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        presenter.presentState(ViewState.INIT);
                                        presenter.presentState(ViewState.END_TRANSITION);
                                    }
                                }, 300L);
                                break;
                            case R.id.train_schedule:
                                presenter.presentState(ViewState.START_TRANSITION);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        presenter.presentState(ViewState.END_TRANSITION);
                                    }
                                }, 300L);
                                break;
                            case R.id.all_route:
                                presenter.presentState(ViewState.START_TRANSITION);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        presenter.presentState(ViewState.END_TRANSITION);
                                    }
                                }, 300L);
                                break;
                            case R.id.setting_app:
                                presenter.presentState(ViewState.START_TRANSITION);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        presenter.presentState(ViewState.END_TRANSITION);
                                    }
                                }, 300L);
                                break;
                            case R.id.about_apaja:
                                presenter.presentState(ViewState.START_TRANSITION);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        presenter.presentState(ViewState.END_TRANSITION);
                                    }
                                }, 300L);
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
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

    private void initNavigationProfile(){

    }

    @Override
    public void showState(ViewState state) {
        switch (state){
            case INIT:
                /* Inisialisasi Main Activity dengan Map dari MainMap Fragment */
                setupHomeFragment();
                initNavigationProfile();
                break;
            case START_TRANSITION:
                showLoadingBar();
                break;
            case END_TRANSITION:
                hideLoadingBar();
                break;
        }
    }
}

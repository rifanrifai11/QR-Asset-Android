package com.politani.britech.qrasset.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.politani.britech.qrasset.R;
import com.politani.britech.qrasset.Utility.MessageDialogFragment;
import com.politani.britech.qrasset.adapter.NavigationBottomViewPagerAdapter;
import com.politani.britech.qrasset.manager.PrefManager;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.google.zxing.Result;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.politani.britech.qrasset.fragment.BaseFragmentBottomNavigation;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements MessageDialogFragment.MessageDialogListener, ZXingScannerView.ResultHandler {

    AHBottomNavigationAdapter navigationAdapter;
    AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
    private boolean useMenuResource = true;
    private int[] tabColors;
    BaseFragmentBottomNavigation currentFragment;
    NavigationBottomViewPagerAdapter adapter;
    private Button logout;
    Context context;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        context=this;
        Context ctx = getApplicationContext();
        action();

    }

    public void action () {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        //appBarLayout = findViewById(R.id.one);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.container_layout);
        AHBottomNavigation.TitleState state = AHBottomNavigation.TitleState.ALWAYS_SHOW;
        bottomNavigation.setTitleState(state);

        if (useMenuResource) {
            tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
            navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.navigation);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
        }

        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (currentFragment == null) {
                    currentFragment = (BaseFragmentBottomNavigation) adapter.getCurrentFragment();
                }

                if (currentFragment != null) {
                    currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);

                currentFragment = (BaseFragmentBottomNavigation) adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();
                return true;
            }
        });

        viewPager.setOffscreenPageLimit(3);
        adapter = new NavigationBottomViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = (BaseFragmentBottomNavigation) adapter.getCurrentFragment();

        logout= (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new AlertDialog.Builder(context)
//                        .setTitle("Logout")
//                        .setMessage("Apakah anda ingin logout dari aplikasi?")
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                Intent i = new Intent(MainActivity.this, Login.class);
//                                startActivity(i);
//                                SharedPreferences preferences =getSharedPreferences("BARCODE_BRITECH", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.clear();
//                                editor.commit();
//                                finish();
//                            }})
//                        .setNegativeButton(android.R.string.no, null).show();

                new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Logout Akun")
                        .setContentText("Apakah anda ingin logout dari aplikasi?")
                        .setConfirmText("Logout")
                        .setCancelText("Batal")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener(){
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                PrefManager prf= new PrefManager(context);
                                prf.remove("token");

                                Intent i = new Intent(MainActivity.this, Login.class);
                                startActivity(i);
                                SharedPreferences preferences =getSharedPreferences("BARCODE_BRITECH", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.commit();
                                finish();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void handleResult(Result result) {

    }
}

package com.politani.britech.qrasset.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.britech.riung.R;
import com.politani.britech.qrasset.Utility.SampleErrorListener;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanBarcodeFragment extends BaseFragmentBottomNavigation {
    public Retrofit retrofit;
    private ZXingScannerView mScannerView;
    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private boolean mFlash;
    private boolean mAutoFocus;
    Context context;
    private FloatingActionButton floatingActionButton1,floatingActionButton2;
    private TextView tvKodeBarang,tvNamaBarang,tvKondisiBarang, tvLokasiBarang,tvPenggunaBarang,tvHargaBeli,tvLamaPemakaian,tvHargaSaatIni;
    private Button bAsetTaking;
    private ImageView imageView;
    private ProgressBar mProgress;
    private MultiplePermissionsListener allPermissionsListener;
    private PermissionListener cameraPermissionListener;
    private PermissionRequestErrorListener errorListener;
    private String foto=null;
    CoordinatorLayout snackBar;

    public ScanBarcodeFragment() {
        // Required empty public constructor
    }

    public static ScanBarcodeFragment newInstance(int index) {
        ScanBarcodeFragment fragment = new ScanBarcodeFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scan_barcode, container, false);
        ViewGroup contentFrame = (ViewGroup) view.findViewById(R.id.content_framee);
        mScannerView = new ZXingScannerView(getContext());
        contentFrame.addView(mScannerView);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.cordinatorLayout);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.focus_fab);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAutoFocus = !mAutoFocus) {
                    mScannerView.setAutoFocus(mAutoFocus);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Auto Focus Active", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    mScannerView.setAutoFocus(mAutoFocus);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Auto Focus Non Active", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
//                mAutoFocus = !mAutoFocus;
//                mScannerView.setAutoFocus(mAutoFocus);
            }
        });
        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.flash_fab);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mFlash = !mFlash) {
                    mScannerView.setFlash(mFlash);
                    floatingActionButton2.setImageResource(R.drawable.ic_flash_on_black_24dp);
                } else {
                    floatingActionButton2.setImageResource(R.drawable.ic_flash_off_black_24dp);
                    mScannerView.setFlash(mFlash);
                }
//                mFlash = !mFlash;
//                mScannerView.setFlash(mFlash);
            }
        });

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FLASH_STATE, mFlash);
        outState.putBoolean(AUTO_FOCUS_STATE, mAutoFocus);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.startCamera();
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
//        closeMessageDialog();
    }

    private void createPermissionListeners() {
//        PermissionListener feedbackViewPermissionListener = new CompositePermissionListener();
        MultiplePermissionsListener feedbackViewMultiplePermissionListener = new CompositeMultiplePermissionsListener();

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(snackBar,
                                R.string.all_permissions_denied_feedback)
                                .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                                .build());

        errorListener = new SampleErrorListener();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(getActivity()).setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_message)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    dialog.dismiss();
                    token.cancelPermissionRequest();
                })
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    token.continuePermissionRequest();
                })
                .setOnDismissListener(dialog -> token.cancelPermissionRequest())
                .show();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

//            mScroll.setVisibility(show ? View.GONE : View.VISIBLE);
//            mScroll.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mScroll.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });

            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgress.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

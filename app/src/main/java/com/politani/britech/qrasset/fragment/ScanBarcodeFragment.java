package com.app.britech.riung.fragment;


import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.britech.riung.R;
import com.app.britech.riung.Utility.SampleErrorListener;
import com.app.britech.riung.activity.AsetTakingActivity;
import com.app.britech.riung.activity.Login;
import com.app.britech.riung.config.Const;
import com.app.britech.riung.manager.PrefManager;
import com.app.britech.riung.models.ResponseDataAset;
import com.app.britech.riung.models.ResponseUser;
import com.app.britech.riung.models.User;
import com.app.britech.riung.service.ApiEndpointService;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanBarcodeFragment extends BaseFragmentBottomNavigation implements ZXingScannerView.ResultHandler {
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

        initializeRetrofit();

        tvKodeBarang=view.findViewById(R.id.tvKodeBarang);
        tvNamaBarang=view.findViewById(R.id.tvNamaBarang);
        tvKondisiBarang=view.findViewById(R.id.tvKondisiBarang);
        tvLokasiBarang=view.findViewById(R.id.tvLokasiBarang);
        tvPenggunaBarang=view.findViewById(R.id.tvPenggunaBarang);
        tvHargaBeli=view.findViewById(R.id.tvHargaBeli);
        tvLamaPemakaian=view.findViewById(R.id.tvLamaPemakaian);
        tvHargaSaatIni=view.findViewById(R.id.tvHargaSaatIni);
        imageView=view.findViewById(R.id.foto);
        mProgress =view.findViewById(R.id.progress);
        snackBar = view.findViewById(R.id.cordinatorLayout);

        bAsetTaking=view.findViewById(R.id.bAsetTaking);

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
                }
                else {
                    floatingActionButton2.setImageResource(R.drawable.ic_flash_off_black_24dp);
                    mScannerView.setFlash(mFlash);
                }
//                mFlash = !mFlash;
//                mScannerView.setFlash(mFlash);
            }
        });

        bAsetTaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvKodeBarang.getText().equals("...")){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Belum Ada User Scan")
                            .setContentText("Scan Barcode Terlebih Dahulu")
                            .setConfirmText("Tutup")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    //getListUser();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }else{
                    Intent intent = new Intent(getActivity(), AsetTakingActivity.class);
                    intent.putExtra("kode_barang", tvKodeBarang.getText());
                    intent.putExtra("nama_barang", tvNamaBarang.getText());
                    intent.putExtra("kondisi_barang", tvKondisiBarang.getText());
                    intent.putExtra("lokasi_barang", tvLokasiBarang.getText());
                    intent.putExtra("pengguna_barang", tvPenggunaBarang.getText());
                    intent.putExtra("harga_beli", tvHargaBeli.getText());
                    intent.putExtra("lama_pemakaian", tvLamaPemakaian.getText());
                    intent.putExtra("harga_saat_ini", tvHargaSaatIni.getText());
                    if(foto!=null){
                        intent.putExtra("foto", foto);
                    }

                    startActivity(intent);
                }


            }
        });

        new Thread(() -> {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Dexter.withActivity(getActivity())
                            .withPermissions(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(allPermissionsListener)
                            .withErrorListener(errorListener)
                            .check();
                }
            }
        }).start();
        createPermissionListeners();
        cekAksesUser();
        return view;
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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
        mScannerView.setResultHandler(this);
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

    @Override
    public void handleResult(Result rawResult) {
        try {
            //showMessageDialog(rawResult.getText());
            showProgress(true);
            PrefManager prf = new PrefManager(getContext());

            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
            Call<ResponseDataAset> result = apiService.getDataAset("Bearer "+prf.getString("token"),rawResult.getText());
            result.enqueue(new Callback<ResponseDataAset>() {
                @Override
                public void onResponse(Call<ResponseDataAset> call, Response<ResponseDataAset> response) {
                    showProgress(false);
                    if(response.isSuccessful() ){
                        if(response.body().getSuccess()){
                            if(response.body().getDataAset()!=null) {
                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
                                r.play();
                                tvKodeBarang.setText(response.body().getDataAset().getKodeDataAset());
                                tvNamaBarang.setText(response.body().getDataAset().getGrubAsets().getNama());

                                if(response.body().getDataAset().getLatestAsetTakings().size()>0){
                                    tvKondisiBarang.setText(response.body().getDataAset().getLatestAsetTakings().get(0).getKondisiAset().getNama());
                                }else{
                                    tvKondisiBarang.setText("...");
                                }

                                tvLokasiBarang.setText(response.body().getDataAset().getLokasi());

                                if(response.body().getDataAset().getAsetBasts().size()>0){
                                    tvPenggunaBarang.setText(response.body().getDataAset().getAsetBasts().get(0).getNama());
                                }else{
                                    tvPenggunaBarang.setText("...");
                                }

                                tvHargaBeli.setText("Rp. "+NumberFormat.getNumberInstance(Locale.US).format(response.body().getDataAset().getAsetPembelian().get(0).getHargaBarang()));
                                tvLamaPemakaian.setText(response.body().getDataAset().getMasaPakaiBulan()+" Bulan / " +response.body().getDataAset().getMasaPakaiTahun()+" Tahun");
                                tvHargaSaatIni.setText("Rp. "+NumberFormat.getNumberInstance(Locale.US).format(response.body().getDataAset().getHargaSekarangBulan()));

                                if(response.body().getDataAset().getFoto1()!=null){
                                    Picasso.with(getContext())
                                            .load(Const.BASE_URL +"storage/"+response.body().getDataAset().getFoto1()).fit()
                                            .placeholder(R.drawable.default_no_image)
                                            .error(R.drawable.default_no_image)
                                            .into(imageView);
                                    foto=response.body().getDataAset().getFoto1();
                                }else{
                                    foto=null;
                                    imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.default_no_image));
                                }

                                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Data Ditemukan")
                                        .setContentText("Data Aset Ditemukan")
                                        .setConfirmText("Tutup")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                //getListUser();
                                                mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();

                            }
                            else {
//                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                                r.play();
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Barcode Tidak Ditemukan")
                                        .setContentText("Barcode Anda Tidak Terdaftar")
                                        .setConfirmText("Tutup")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                //getListUser();
                                                mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        }else{
//                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                            r.play();
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Aplikasi Bermasalah")
                                    .setContentText(response.body().getMessage())
                                    .setConfirmText("Tutup")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            //getListUser();
                                            mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();
                        }
                    }else{
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());

                            if(jObjError.has("error") && jObjError.getString("error").equalsIgnoreCase("token_expired")){
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Aplikasi Bermasalah")
                                        .setContentText("Login anda telah kadaluarsa. Silahkan Login ulang")
                                        .setConfirmText("Login Ulang")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                PrefManager prf= new PrefManager(getContext());
                                                prf.remove("token");
                                                Intent intent= new Intent(getActivity(),Login.class);
                                                startActivity(intent);
                                                getActivity().finish();
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }else if(jObjError.has("message")){
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Gagal")
                                        .setContentText(jObjError.getString("message"))
                                        .setConfirmText("Tutup")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }else{
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Aplikasi Bermasalah")
                                        .setContentText("Nomor Sambungan tidak ditemukan atau Respon server bermasalah")
                                        .setConfirmText("Tutup")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                //getAccessToken();
                                                mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        } catch (Exception e) {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Pesan Aplikasi")
                                    .setContentText("Tidak ada tagihan untuk nomor ini")
                                    .setConfirmText("Cari Ulang")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseDataAset> call, Throwable t) {
                    showProgress(false);
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Aplikasi Bermasalah")
                            .setContentText("Error "+t.getMessage())
                            .setConfirmText("Tutup")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    //getListUser();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            });


        } catch (Exception e) {
            showProgress(false);
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Aplikasi Bermasalah")
                    .setContentText("Error "+e.getMessage())
                    .setConfirmText("Tutup")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            //getListUser();
                            mScannerView.resumeCameraPreview(ScanBarcodeFragment.this::handleResult);
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }

    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    //Cek Akses Aset Taking
    private void cekAksesUser() {
        try {
            PrefManager prf = new PrefManager(getContext());

            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
            Call<ResponseUser> result = apiService.getUser("Bearer "+prf.getString("token"));
            result.enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    if(response.isSuccessful()){
                        if (response.body().getSuccess()) {
                            User user=response.body().getUser();
                            bAsetTaking.setVisibility(View.GONE);
                            //Cek Role
                            for (int i = 0; i < user.getRoles().size(); i++) {
                                if(user.getRoles().get(i).getName().equals("admin_aset_taking")){
                                    bAsetTaking.setVisibility(View.VISIBLE);
                                    break;
                                }else{

                                    continue;
                                }
                            }


                        } else {
                        }
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                }
            });


        } catch (Exception e) {
        }

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
}

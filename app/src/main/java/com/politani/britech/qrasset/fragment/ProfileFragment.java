package com.app.britech.riung.fragment;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.britech.riung.R;
import com.app.britech.riung.Utility.SampleErrorListener;
import com.app.britech.riung.activity.Login;
import com.app.britech.riung.config.Const;
import com.app.britech.riung.manager.PrefManager;
import com.app.britech.riung.models.ResponseDataAset;
import com.app.britech.riung.models.ResponseListDataAset;
import com.app.britech.riung.models.ResponseUser;
import com.app.britech.riung.models.User;
import com.app.britech.riung.room.App;
import com.app.britech.riung.room.DataAset;
import com.app.britech.riung.service.ApiEndpointService;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragmentBottomNavigation  {

    private CircleImageView mProfileImage;
    private CoordinatorLayout snackBar;
    private MultiplePermissionsListener allPermissionsListener;
    private PermissionListener cameraPermissionListener;
    private PermissionRequestErrorListener errorListener;
    private Uri uriImageProfil;
    private Retrofit retrofit;
    private Button buttonSyncDataAset;

    private EditText eName,eEmail,eAlamat,ePhone;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(int index) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        addContainer(view.findViewById(R.id.cordinatorLayout));

        initializeRetrofit();

        eName=view.findViewById(R.id.eNama);
        eAlamat=view.findViewById(R.id.eAlamat);
        eEmail=view.findViewById(R.id.eEmail);
        ePhone=view.findViewById(R.id.ePhone);

        snackBar = view.findViewById(R.id.cordinatorLayout);
        mProfileImage = view.findViewById(R.id.image_profil);
        buttonSyncDataAset=view.findViewById(R.id.sync_data_aset);

        buttonSyncDataAset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncDataAset();
            }
        });

        getDataUser();
        updateDataUser();

        return view;
    }

    private void createPermissionListeners() {
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
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void updateDataUser() {
        mProfileImage.setOnClickListener(v -> {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getContext())
                            .setTitle(getResources().getString(R.string.choose_upload_foto_profil))
                            .setOnImageSelectedListener(uri -> {
                                Picasso.with(getContext())
                                        .load(uri)
                                        .transform(new CropCircleTransformation())
                                        .resize(80, 80)
                                        .centerCrop()
                                        .placeholder(R.drawable.ic_flash_off_black_24dp)
                                        .into(mProfileImage);
                                uriImageProfil=uri;
                            })
                            .create();
                    tedBottomPicker.show(getFragmentManager());
                } else {
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
        });
        createPermissionListeners();
    }

    public void getDataUser(){
        //showProgress(true);
        try {
            PrefManager prf = new PrefManager(getContext());

            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
            Call<ResponseUser> result = apiService.getUser(
                    "Bearer "+prf.getString("token"));
            result.enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    //showProgress(false);
                    if(response.isSuccessful() ){
                        if(response.body().getSuccess()){
                            User user=response.body().getUser();
                            eName.setText(user.getName());
                            ePhone.setText(user.getKontak());
                            eAlamat.setText(user.getAlamat());
                            eEmail.setText(user.getEmail());

                            /*Picasso.with(getContext())
                                    .load(Const.BASE_URL +user.getFoto())
                                    .transform(new CropCircleTransformation())
                                    .resize(80, 80)
                                    .centerCrop()
                                    .placeholder(R.drawable.profile2)
                                    .into(profileImage);*/

                        }
                    }else{

                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());

                            if(jObjError.getString("error").equalsIgnoreCase("token_expired")){
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
                            }else{
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Aplikasi Bermasalah")
                                        .setContentText("Respon server bermasalah")
                                        .setConfirmText("Tutup")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                //getAccessToken();
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //showMessageDialog("Sistem Bermasalah Hubungi Administrator");
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    //showProgress(false);
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Aplikasi Bermasalah")
                            .setContentText("Koneksi / Jaringan Internet Bermasalah")
                            .setConfirmText("Tutup")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    //getAccessToken();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }


            });

        } catch (Exception e) {
            //showProgress(false);
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Aplikasi Bermasalah")
                    .setContentText("Koneksi / Jaringan Internet Bermasalah")
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
    }

    public void syncDataAset(){
        //showProgress(true);
        try {
            PrefManager prf = new PrefManager(getContext());

            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
            Call<ResponseListDataAset> result = apiService.syncDataAset(
                    "Bearer "+prf.getString("token"));
            result.enqueue(new Callback<ResponseListDataAset>() {
                @Override
                public void onResponse(Call<ResponseListDataAset> call, Response<ResponseListDataAset> response) {
                    //showProgress(false);
                    if(response.isSuccessful() ){
                        if(response.body().getSuccess()){

                            Handler mHandler = new Handler();

                            new Thread(new Runnable() {
                                @Override
                                public void run () {
                                    App.database.getDataAsetDao().emptyTable();
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run () {

                                            for (int i=0;i<response.body().getData().size();i++){
                                                DataAset dataAset=new DataAset();
                                                dataAset.setCreatedAt(response.body().getData().get(i).getCreatedAt());
                                                dataAset.setDeletedAt(response.body().getData().get(i).getDeletedAt());
                                                dataAset.setDepartemenId(response.body().getData().get(i).getDepartemenId());
                                                dataAset.setGrubAsetKode(response.body().getData().get(i).getGrubAsetKode());
                                                dataAset.setHargaSekarangBulan(response.body().getData().get(i).getHargaSekarangBulan());
                                                dataAset.setHargaSekarangTahun(response.body().getData().get(i).getHargaSekarangTahun());
                                                dataAset.setId(response.body().getData().get(i).getId());
                                                dataAset.setJobsiteId(response.body().getData().get(i).getJobsiteId());
                                                dataAset.setKodeDataAset(response.body().getData().get(i).getKodeDataAset());
                                                dataAset.setLokasi(response.body().getData().get(i).getLokasi());
                                                dataAset.setMasaPakaiBulan(response.body().getData().get(i).getMasaPakaiBulan());
                                                dataAset.setMasaPakaiTahun(response.body().getData().get(i).getMasaPakaiTahun());
                                                dataAset.setMerekId(response.body().getData().get(i).getMerekId());
                                                dataAset.setNilaiSisa(response.body().getData().get(i).getNilaiSisa());
                                                dataAset.setNoRegistrasi(response.body().getData().get(i).getNoRegistrasi());
                                                dataAset.setPenyusutanPerBulan(response.body().getData().get(i).getPenyusutanPerBulan());
                                                dataAset.setPenyusutanPerTahun(response.body().getData().get(i).getPenyusutanPerTahun());
                                                dataAset.setSerialNumber(response.body().getData().get(i).getSerialNumber());
                                                dataAset.setSpesifikasi(response.body().getData().get(i).getSpesifikasi());
                                                dataAset.setTanggalRegistrasi(response.body().getData().get(i).getTanggalRegistrasi());
                                                dataAset.setTipeId(response.body().getData().get(i).getTipeId());
                                                dataAset.setUrut(response.body().getData().get(i).getUrut());
                                                dataAset.setVendorId(response.body().getData().get(i).getVendorId());

                                                Thread thread2 = new Thread() {
                                                    @Override
                                                    public void run() {
                                                        App.database.getDataAsetDao().create(dataAset);
                                                    }
                                                };

                                                thread2.start();
                                            }

                                            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("Sukses")
                                                    .setContentText("Sinkronisasi Data Aset Sukses")
                                                    .setConfirmText("Tutup")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sDialog) {
                                                            //getAccessToken();
                                                            sDialog.dismissWithAnimation();
                                                        }
                                                    })
                                                    .show();

                                        }
                                    });
                                }
                            }).start();




                        }
                    }else{

                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());

                            if(jObjError.getString("error").equalsIgnoreCase("token_expired")){
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
                            }else{
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Aplikasi Bermasalah")
                                        .setContentText("Respon server bermasalah")
                                        .setConfirmText("Tutup")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                //getAccessToken();
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //showMessageDialog("Sistem Bermasalah Hubungi Administrator");
                    }
                }

                @Override
                public void onFailure(Call<ResponseListDataAset> call, Throwable t) {
                    //showProgress(false);
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Aplikasi Bermasalah")
                            .setContentText("Koneksi / Jaringan Internet Bermasalah")
                            .setConfirmText("Tutup")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    //getAccessToken();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }


            });

        } catch (Exception e) {
            //showProgress(false);
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Aplikasi Bermasalah")
                    .setContentText("Koneksi / Jaringan Internet Bermasalah")
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
    }
}

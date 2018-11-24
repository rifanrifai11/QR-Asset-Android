package com.politani.britech.qrasset.fragment;


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

import com.app.britech.riung.Utility.SampleErrorListener;
import com.app.britech.riung.config.Const;
import com.app.britech.riung.manager.PrefManager;
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
import com.politani.britech.qrasset.R;
import com.politani.britech.qrasset.fragment.BaseFragmentBottomNavigation;
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
public class ProfileFragment extends BaseFragmentBottomNavigation {

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

//        buttonSyncDataAset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

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
}

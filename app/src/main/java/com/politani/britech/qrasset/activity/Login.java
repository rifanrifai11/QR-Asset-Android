package com.politani.britech.qrasset.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import com.politani.britech.qrasset.R;
import com.politani.britech.qrasset.Utility.MessageDialogFragment;
import com.politani.britech.qrasset.config.Const;
import com.politani.britech.qrasset.manager.PrefManager;
import com.politani.britech.qrasset.models.ResponseToken;
import com.politani.britech.qrasset.service.ApiEndpointService;
import com.xwray.passwordview.PasswordView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements MessageDialogFragment.MessageDialogListener {

    private EditText mEmailView;
    private PasswordView mPasswordView;
    private Button loginButton;
    public Retrofit retrofit;
    public ProgressBar mProgress;
    public ScrollView mScroll;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.login_activity);

        PrefManager prf = new PrefManager(getApplicationContext());
        if(!prf.getString("token").equals("")){
            startMainActivity();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeRetrofit();

        mProgress = (ProgressBar) findViewById(R.id.login_progress);
        mScroll = (ScrollView) findViewById(R.id.progress_layout);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (PasswordView) findViewById(R.id.password);
        loginButton= (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        SharedPreferences settings = getSharedPreferences("BARCODE_BRITECH",Context.MODE_PRIVATE);
        if(settings.getBoolean("LOGIN", false)){
            /*openDashboard(settings.getString("ROLES",""));*/
        }

    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getAccessToken(String email,String password){
        try {
            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
            Call<ResponseToken> result = apiService.getAccessToken(email,password);
            result.enqueue(new Callback<ResponseToken>() {
                @Override
                public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                    showProgress(false);
                    if(response.isSuccessful()){
                        if(response.body().getSuccess()){

                            PrefManager prf= new PrefManager(getApplicationContext());
                            prf.setString("token",response.body().getData().getToken());

                            //Untuk firebase token
                            /*ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
                            Call<ResponseUser> result = apiService.setTokenDevice("Bearer "+prf.getString("token"),FirebaseInstanceId.getInstance().getToken());
                            result.enqueue(new Callback<ResponseUser>() {
                                @Override
                                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                                    if(response.isSuccessful()){
                                        Log.v("AFTER LOGIN","Hassan : "+response.body().getMessage());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseUser> call, Throwable t) {
                                    *//*Snackbar snackbar = Snackbar
                                            .make(relativeLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                                            .setAction("RETRY", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    getComment();
                                                }
                                            });
                                    snackbar.setActionTextColor(Color.RED);
                                    View sbView = snackbar.getView();
                                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                    textView.setTextColor(Color.YELLOW);
                                    snackbar.show();*//*
                                }
                            });*/

                            startMainActivity();
                        }else{
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                            r.play();
                            //showMessageDialog(response.body().getMessage());
                        }
                    }else{
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());

                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Login gagal")
                                    .setContentText(jObjError.getString("message"))
                                    .setConfirmText("Tutup")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            //getAccessToken();
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseToken> call, Throwable t) {
                    showProgress(false);
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
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
            showProgress(false);
            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
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

    private void startMainActivity() {
        Intent intent= new Intent(Login.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        //mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        mPasswordView.setText("");
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            getAccessToken(email,password);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
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

    /*private void openDashboard(String strRoles) {
        String[] roles = strRoles.split(",");

        if (Arrays.asList(roles).contains("penerima_tamu")) {
            Intent i = new Intent(Login.this, Dashboard.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }*//*else if (Arrays.asList(roles).contains("tim_expo")) {
            Intent i = new Intent(Login.this, DashboardExpo.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }*//*else{
            showMessageDialog("Anda Tidak Memiliki Hak Akses. Hubungi Administrator");
            mPasswordView.setText("");
        }
    }*/

    public void showMessageDialog(String message) {
        DialogFragment fragment = MessageDialogFragment.newInstance("Proses Login", message, this);
        fragment.show(getSupportFragmentManager(), "login_results");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.dismiss();
    }
}


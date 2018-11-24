package com.politani.britech.qrasset.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.politani.britech.qrasset.config.Const;
import com.politani.britech.qrasset.R;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

public class AsetTakingActivity extends AppCompatActivity {
    private TextView tvKodeBarang,tvNamaBarang,tvKondisiBarang, tvLokasiBarang,tvPenggunaBarang,tvHargaBeli,tvLamaPemakaian,tvHargaSaatIni;
    private Spinner spinnerKondisiAset;
    private  Retrofit retrofit;
    private Context context;
//    private List<KondisiAset> listKondisiAset;
    private String selectedKondisiAsetId;
    private Button bSimpanAsetTaking;
    private ImageView imageView;
    public ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aset_taking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context=this;

        mProgress=findViewById(R.id.progress);
        spinnerKondisiAset=findViewById(R.id.spinnerKondisiSaatIni);
        bSimpanAsetTaking=findViewById(R.id.bSimpanAsetTaking);
        tvKodeBarang=findViewById(R.id.tvKodeBarang);
        tvNamaBarang=findViewById(R.id.tvNamaBarang);
        tvKondisiBarang=findViewById(R.id.tvKondisiBarang);
        tvLokasiBarang=findViewById(R.id.tvLokasiBarang);
        tvPenggunaBarang=findViewById(R.id.tvPenggunaBarang);
        tvHargaBeli=findViewById(R.id.tvHargaBeli);
        tvLamaPemakaian=findViewById(R.id.tvLamaPemakaian);
        tvHargaSaatIni=findViewById(R.id.tvHargaSaatIni);
        imageView=findViewById(R.id.foto);

        tvKodeBarang.setText(getIntent().getStringExtra("kode_barang"));
        tvNamaBarang.setText(getIntent().getStringExtra("nama_barang"));
        tvKondisiBarang.setText(getIntent().getStringExtra("kondisi_barang"));
        tvLokasiBarang.setText(getIntent().getStringExtra("lokasi_barang"));
        tvPenggunaBarang.setText(getIntent().getStringExtra("pengguna_barang"));
        tvHargaBeli.setText(getIntent().getStringExtra("harga_beli"));
        tvLamaPemakaian.setText(getIntent().getStringExtra("lama_pemakaian"));
        tvHargaSaatIni.setText(getIntent().getStringExtra("harga_saat_ini"));

        if(getIntent().hasExtra("foto")){
            Picasso.with(context)
                    .load(Const.BASE_URL +"storage/"+getIntent().getStringExtra("foto")).fit()
                    .placeholder(R.drawable.default_no_image)
                    .error(R.drawable.default_no_image)
                    .into(imageView);
        }else{
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.default_no_image));
        }

//        initializeRetrofit();
//
//        getKondisiAset();
//
//        bSimpanAsetTaking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
//                        .setTitleText("Konfirmasi Aset Taking")
//                        .setContentText("Menambahkan kondisi Aset Taking ?")
//                        .setCancelText("Tidak")
//                        .setConfirmText("Ya")
//                        .showCancelButton(true)
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                storeAsetTaking();
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
//                        .show();
//
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void getKondisiAset(){
//        try {
//            PrefManager prf = new PrefManager(this);
//            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
//            Call<ResponseKondisiAset> result = apiService.getKondisiAset(
//                    "Bearer "+prf.getString("token"));
//
//            result.enqueue(new Callback<ResponseKondisiAset>() {
//                @Override
//                public void onResponse(Call<ResponseKondisiAset> call, Response<ResponseKondisiAset> response) {
//                    if(response.isSuccessful() ){
//                        if(response.body().getSuccess()){
//
//                            listKondisiAset=response.body().getKondisiAset();
//                            if(listKondisiAset.size()>0){
//                                final List<String> listId = new ArrayList<>(listKondisiAset.size());
//                                List<String> listNama= new ArrayList<>(listKondisiAset.size());
//                                for (KondisiAset kondisiAset: listKondisiAset) {
//                                    listId.add(kondisiAset.getId());
//                                    listNama.add(kondisiAset.getNama());
//                                }
//                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listNama);
//                                spinnerKondisiAset.setAdapter(adapter);
//
//                                spinnerKondisiAset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                                    @Override
//                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                        selectedKondisiAsetId = listId.get(i).toString();
//                                    }
//
//                                    @Override
//                                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                                    }
//                                });
//                            }
//
//                        }else{
//                        }
//                    }else{
//                        try {
//                            JSONObject jObjError = new JSONObject(response.errorBody().string());
//
//                            if(jObjError.getString("error").equalsIgnoreCase("token_expired")){
//                                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                                        .setTitleText("Aplikasi Bermasalah")
//                                        .setContentText("Login anda telah kadaluarsa. Silahkan Login ulang")
//                                        .setConfirmText("Login Ulang")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                PrefManager prf= new PrefManager(context);
//                                                prf.remove("token");
//                                                Intent intent= new Intent(AsetTakingActivity.this,Login.class);
//                                                startActivity(intent);
//                                                finish();
//                                                sDialog.dismissWithAnimation();
//                                            }
//                                        })
//                                        .show();
//                            }else{
//                                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                                        .setTitleText("Aplikasi Bermasalah")
//                                        .setContentText("Respon server bermasalah")
//                                        .setConfirmText("Tutup")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                //getAccessToken();
//                                                sDialog.dismissWithAnimation();
//                                            }
//                                        })
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        //showMessageDialog("Sistem Bermasalah Hubungi Administrator");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseKondisiAset> call, Throwable t) {
//                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("Aplikasi Bermasalah")
//                            .setContentText("Koneksi / Jaringan Internet Bermasalah")
//                            .setConfirmText("Tutup")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                }
//                            })
//                            .show();
//                }
//
//            });
//
//        } catch (Exception e) {
//            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("Aplikasi Bermasalah")
//                    .setContentText("Koneksi / Jaringan Internet Bermasalah")
//                    .setConfirmText("Tutup")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
//                    .show();
//        }
//    }
//
//    public void storeAsetTaking(){
//        try {
//            showProgress(true);
//            PrefManager prf = new PrefManager(this);
//            ApiEndpointService apiService = retrofit.create(ApiEndpointService.class);
//            Call<ResponseStoreAsetTaking> result = apiService.setAsetTaking(
//                    "Bearer "+prf.getString("token"),tvKodeBarang.getText().toString(),selectedKondisiAsetId);
//
//            result.enqueue(new Callback<ResponseStoreAsetTaking>() {
//                @Override
//                public void onResponse(Call<ResponseStoreAsetTaking> call, Response<ResponseStoreAsetTaking> response) {
//                    showProgress(false);
//                    if(response.isSuccessful() ){
//                        if(response.body().getSuccess()){
//                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setTitleText("Aset Taking Sukses")
//                                    .setContentText("Aset Taking Telah Disimpan")
//                                    .setConfirmText("Ok")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            finish();
//                                            sDialog.dismissWithAnimation();
//                                        }
//                                    })
//                                    .show();
//
//                        }else{
//                        }
//                    }else{
//                        try {
//                            JSONObject jObjError = new JSONObject(response.errorBody().string());
//
//                            if(jObjError.getString("error").equalsIgnoreCase("token_expired")){
//                                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                                        .setTitleText("Aplikasi Bermasalah")
//                                        .setContentText("Login anda telah kadaluarsa. Silahkan Login ulang")
//                                        .setConfirmText("Login Ulang")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                PrefManager prf= new PrefManager(context);
//                                                prf.remove("token");
//                                                Intent intent= new Intent(AsetTakingActivity.this,Login.class);
//                                                startActivity(intent);
//                                                finish();
//                                                sDialog.dismissWithAnimation();
//                                            }
//                                        })
//                                        .show();
//                            }else{
//                                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                                        .setTitleText("Aplikasi Bermasalah")
//                                        .setContentText("Respon server bermasalah")
//                                        .setConfirmText("Tutup")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                //getAccessToken();
//                                                sDialog.dismissWithAnimation();
//                                            }
//                                        })
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        //showMessageDialog("Sistem Bermasalah Hubungi Administrator");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseStoreAsetTaking> call, Throwable t) {
//                    showProgress(false);
//                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("Aplikasi Bermasalah")
//                            .setContentText("Koneksi / Jaringan Internet Bermasalah")
//                            .setConfirmText("Tutup")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                }
//                            })
//                            .show();
//                }
//
//            });
//
//        } catch (Exception e) {
//            showProgress(false);
//            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("Aplikasi Bermasalah")
//                    .setContentText("Koneksi / Jaringan Internet Bermasalah")
//                    .setConfirmText("Tutup")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
//                    .show();
//        }
//    }
//
//    private void initializeRetrofit(){
//        retrofit = new Retrofit.Builder()
//                .baseUrl(Const.BASE_API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }

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

package com.example.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String BASE_URL="https://jsonplaceholder.typicode.com/";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api=retrofit.create(ApiInterface.class);
        Call<List<Modelclass>> call=api.getresult();
        call.enqueue(new Callback<List<Modelclass>>() {
            @Override
            public void onResponse(Call<List<Modelclass>> call, Response<List<Modelclass>> response) {
                List<Modelclass> data=response.body();
                for(int i=0;i<data.size();i++){
                    tv.append("SL NO"+data.get(i).getId()+" \n Title :"+data.get(i).getTitle()+"\n\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<Modelclass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Loding Failed"
                        , Toast.LENGTH_SHORT).show();

            }
        });
    }
}
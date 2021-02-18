package fstt.hamza.exerciceAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listView;
    ArrayList<User> arrayList;
    UserAdapter arrayAdapter;
    ArrayList<User> users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: Started");
        listView = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<User>();
        arrayAdapter = new UserAdapter(this, R.layout.list_adaptater_view,arrayList);
        listView.setAdapter(arrayAdapter);
    }
    public void fetchUser(View view){
        TextView FnameView = (TextView) findViewById(R.id.Fname);
        EditText userID = (EditText) findViewById(R.id.nbUser);
        // Connexion avec l'API
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<OneUser> call = apiInterface.getUsers( Integer.parseInt(userID.getText().toString()));
        call.enqueue(new Callback<OneUser>() {
            @Override
            public void onResponse(Call<OneUser> call, Response<OneUser> response) {

                arrayList.clear();
                User tmpuser =new User(response.body().getId(),response.body().getEmail(),response.body().getFname(),response.body().getLname(),response.body().getAvatar());
                arrayList.add(tmpuser);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OneUser> call, Throwable t) {

            }
        });
    }
    public void fetchAll(View view){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<AllUsers> call = apiInterface.getAll();
        call.enqueue(new Callback<AllUsers>() {
            @Override
            public void onResponse(Call<AllUsers> call, Response<AllUsers> response) {
                arrayList.clear();
                users = (ArrayList<User>) response.body().getAll();
                arrayList.addAll(users);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AllUsers> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}
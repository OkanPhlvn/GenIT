package online.genit.genitonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    private List<HomeContent> contentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HomeContentAdapter contentAdapter;

    String url = "https://genit.online/home/index/mobile";

    ImageView logo, todo, gen, genner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Initialization();


        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void Initialization() {
        recyclerView = findViewById(R.id.recyclerView);



        todo = findViewById(R.id.todo);
        gen = findViewById(R.id.gen);
        genner = findViewById(R.id.genner);
        logo = findViewById(R.id.logo);

        todo.setOnClickListener(this);
        gen.setOnClickListener(this);
        genner.setOnClickListener(this);
        logo.setOnClickListener(this);
    }

    void getData() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                final String jsonString = response.body().string();

                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(jsonString);

                            int resID = HomeActivity.this.getResources().getIdentifier("g", "drawable", HomeActivity.this.getPackageName());

                            contentList.add(new HomeContent(resID,"Gens","","- - - Recent Gens - - -", false));

                            JSONArray gensJsonArray = jsonObject.getJSONArray("gens");
                            for (int i=0;i<gensJsonArray.length();i++) {
                                String icon = "icon" + gensJsonArray.getJSONObject(i).getString("icon");
                                resID = HomeActivity.this.getResources().getIdentifier(icon, "drawable", HomeActivity.this.getPackageName());

                                String userName = gensJsonArray.getJSONObject(i).getString("name");
                                String createDate = gensJsonArray.getJSONObject(i).getString("create_date");
                                String content = "" + gensJsonArray.getJSONObject(i).getString("content");

                                contentList.add(new HomeContent(resID,userName,createDate,content, true));
                            }

                            resID = HomeActivity.this.getResources().getIdentifier("t", "drawable", HomeActivity.this.getPackageName());

                            contentList.add(new HomeContent(resID,"Todos","","- - - Recent Todos - - -",false));

                            JSONArray todosJsonArray = jsonObject.getJSONArray("todos");
                            for (int i=0;i<todosJsonArray.length();i++) {
                                String icon = "icon" + todosJsonArray.getJSONObject(i).getString("icon");
                                resID = HomeActivity.this.getResources().getIdentifier(icon, "drawable", HomeActivity.this.getPackageName());

                                String userName = todosJsonArray.getJSONObject(i).getString("name");
                                String createDate = todosJsonArray.getJSONObject(i).getString("create_date");
                                String content = todosJsonArray.getJSONObject(i).getString("description");

                                contentList.add(new HomeContent(resID,userName,createDate,content,true));
                            }

                            contentAdapter = new HomeContentAdapter(contentList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);

                            recyclerView.setAdapter(contentAdapter);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == logo)
        {
            Toast.makeText(getApplicationContext(),"Easy boy...",Toast.LENGTH_SHORT).show();
        }
        else if(v == gen)
        {
            Intent intent = new Intent(HomeActivity.this, GensActivity.class);
            startActivity(intent);
        }
        else if(v == todo)
        {
            Intent intent = new Intent(HomeActivity.this, GensActivity.class);
            startActivity(intent);
        }
        else if(v == genner)
        {
            Toast.makeText(getApplicationContext(),"This Activity Is Not Enabled, YET!", Toast.LENGTH_LONG).show();
        }
    }
}


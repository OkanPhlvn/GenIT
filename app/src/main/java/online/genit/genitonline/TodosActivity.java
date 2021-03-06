package online.genit.genitonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TodosActivity extends AppCompatActivity {

    TextView txt;
    String url = "https://genit.online/todos/index/mobile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);


        txt = findViewById(R.id.textView);


        try {
            getJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void getJSON() throws IOException {

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

                final String myResponse = response.body().string();

                TodosActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(myResponse);
                    }
                });

            }
        });
    }
}

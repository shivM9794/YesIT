package app.yesit.HomeSection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.yesit.Adapters.HeroAdapter;
import app.yesit.R;
import app.yesit.ResponseModel.Example;
import app.yesit.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    final String URL_GET_DATA = "https://simplifiedcoding.net/demos/marvel/";
    HeroAdapter adapter;
    List<Example> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        binding.recyclerMarvel.setHasFixedSize(true);
        binding.recyclerMarvel.setLayoutManager(new LinearLayoutManager(this));
        heroList = new ArrayList<>();
        loadHeroes();
        binding.musicPlayer.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MusicActivity.class);
            startActivity(intent);
        });
    }

    private void loadHeroes() {
        binding.spinKit.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_DATA,
                response -> {
                    try {
                        binding.spinKit.setVisibility(View.GONE);
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            Example example = new Example(
                                    obj.getString("name"),
                                    obj.getString("realname"),
                                    obj.getString("team"),
                                    obj.getString("firstappearance"),
                                    obj.getString("createdby"),
                                    obj.getString("publisher"),
                                    obj.getString("imageurl"),
                                    obj.getString("bio")
                            );

                            heroList.add(example);
                        }

                        adapter = new HeroAdapter(heroList, getApplicationContext());
                        binding.recyclerMarvel.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> binding.spinKit.setVisibility(View.GONE));


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

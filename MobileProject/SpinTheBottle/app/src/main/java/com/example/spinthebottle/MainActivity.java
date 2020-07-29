package com.example.spinthebottle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private ImageView bottle;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottle = findViewById(R.id.bottle);

    }

    public void spinBottle(View v) {
        if (!spinning) {
            int newDir = random.nextInt(1800);
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;
            TextView output;

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            lastDir = newDir;
            bottle.startAnimation(rotate);

            float remain = (float)newDir/(float)360 % 1;
            System.out.println(remain);

            if (remain > 0.25 & remain < 0.75) {
                System.out.println("Pokemon");
                String poke_win = ("Pokemon won");
                TextView winner = findViewById(R.id.textView4);
                winner.setText(poke_win);

            }
            else {
                System.out.println("Yu Gi Oh");
                String yugi_win = ("Yu Gi Oh won");
                TextView winner = findViewById(R.id.textView4);
                winner.setText(yugi_win);
            }
        }
        RequestQueue requestQueue=Volley.newRequestQueue(this);

        String URL = "https://pokeapi.co/api/v2/pokemon?limit=151";

        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        System.out.println(response);
                        try {
                            Random num = new Random();
                            JSONArray results = response.getJSONArray("results");
                            JSONObject index = results.getJSONObject(num.nextInt(150));
                            String name = index.getString("name");
                            System.out.println(name);
                            TextView output = findViewById(R.id.textView3);
                            output.setText(name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());

                    }
                }
        );

        requestQueue.add(objectRequest);

        RequestQueue requestQueue2=Volley.newRequestQueue(this);

        String URL2 = "https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Normal Monster";

        JsonObjectRequest objectRequest2=new JsonObjectRequest(
                Request.Method.GET,
                URL2,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        System.out.println(response);
                        try {
                            Random num = new Random();
                            JSONArray results = response.getJSONArray("data");
                            JSONObject index = results.getJSONObject(num.nextInt(50));
                            String name = index.getString("name");
                            System.out.println(name);
                            TextView output2 = findViewById(R.id.textView2);
                            output2.setText(name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());

                    }
                }
        );

        requestQueue2.add(objectRequest2);
    }

}
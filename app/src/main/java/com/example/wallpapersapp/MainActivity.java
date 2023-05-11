package com.example.wallpapersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements categoryRvAdapter.CategoryClickInterface {

    private EditText searchEdt;
    private ImageView  searchIv;
    private RecyclerView categoryRv;
    private  RecyclerView wallpapersRv;
    private ProgressBar loadingPb;

    private ArrayList<String> WallpaperArrayList;
    private ArrayList<categoryRvModel>  CategoryRvModelArrayList;
    private categoryRvAdapter CategoryRvAdapter;
    private wallpaperRvAdapter WallpaperRvAdapter;
    // EvyEg2sRfLLbRZnTyKqmhSkNB23pA3fG0pcyDXXJpDTkEuggDZ6Vsb2F





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEdt = findViewById(R.id.idEdtSearch);
        searchIv = findViewById(R.id.idIvSearch);
        categoryRv = findViewById(R.id.idRvCategory);
        wallpapersRv = findViewById(R.id.idRvWallpapers);
        loadingPb = findViewById(R.id.idPbLoading);

        WallpaperArrayList = new ArrayList<>();
        CategoryRvModelArrayList = new ArrayList<>();
        LinearLayoutManager  linearLayoutManager  = new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        categoryRv.setLayoutManager(linearLayoutManager);
        CategoryRvAdapter = new categoryRvAdapter(CategoryRvModelArrayList , this , this::onCategoryClick);
        categoryRv.setAdapter(CategoryRvAdapter);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
        wallpapersRv.setLayoutManager(gridLayoutManager);
        WallpaperRvAdapter = new wallpaperRvAdapter(WallpaperArrayList,this);
        wallpapersRv.setAdapter(WallpaperRvAdapter);

        getCategories();
        getWallpapers();

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchStr = searchEdt.getText().toString();
                if (searchStr.isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter search query", Toast.LENGTH_SHORT).show();
                }else {
                    getWallpapersBYCategory(searchStr);

                }
            }
        });
        
    }
    private void getWallpapersBYCategory(String category){
        WallpaperArrayList.clear();
        loadingPb.setVisibility(View.VISIBLE);
        String url = "https://api.pexels.com/v1/search?query="+category+"&per_page=30&page=1";
RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
    @Override
    public void onResponse(JSONObject response) {

    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}){
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
      HashMap<String,String>headers =  new HashMap<>();
        headers.put("Authorization" , "EvyEg2sRfLLbRZnTyKqmhSkNB23pA3fG0pcyDXXJpDTkEuggDZ6Vsb2F");
      return headers;
    }
};
requestQueue.add(jsonObjectRequest);


    }

    private void getWallpapers() {
        WallpaperArrayList.clear();
        loadingPb.setVisibility(View.VISIBLE);
        String url = "https://api.pexels.com/v1/curated?per_page=30&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPb.setVisibility(View.GONE);

                try {
                    JSONArray photoArray  = response.getJSONArray("photos");
                    for (int i = 0; i<photoArray.length(); i++){
                        JSONObject photoObj = photoArray.getJSONObject(i);
                        String imgUrl = photoObj.getJSONObject("src").getString("portrait");
                        WallpaperArrayList.add(imgUrl);

                    }
                    WallpaperRvAdapter.notifyDataSetChanged();

                }catch (JSONException e){
                    e.printStackTrace();

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to load wallpapers...", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String , String> headers = new HashMap<>();
                headers.put("Authorization" , "EvyEg2sRfLLbRZnTyKqmhSkNB23pA3fG0pcyDXXJpDTkEuggDZ6Vsb2F");
                return headers;

            }
        };
        requestQueue.add(jsonObjectRequest);


    }

    private  void  getCategories(){
        CategoryRvModelArrayList.add(new categoryRvModel("Technology" ,"https://wallup.net/wp-content/uploads/2019/09/355028-smartphone-wallpaper-icons-scheme-phone-computer.jpg"));
        CategoryRvModelArrayList.add(new categoryRvModel("Programming" ,"https://w0.peakpx.com/wallpaper/123/322/HD-wallpaper-programmer-code-coding-dark-program-thumbnail.jpg"));
        CategoryRvModelArrayList.add(new categoryRvModel("Nature" ,"https://www.mordeo.org/download/19052/"));
        CategoryRvModelArrayList.add(new categoryRvModel("Travel" ,"https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTd8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80"));
        CategoryRvModelArrayList.add(new categoryRvModel("Architecture" ,"https://wallpapersmug.com/download/320x480/0a8b27/edge-of-building-architecture.jpg"));
        CategoryRvModelArrayList.add(new categoryRvModel("Arts" ,"https://wallpaperaccess.com/full/1870366.png"));
        CategoryRvModelArrayList.add(new categoryRvModel("Music" ,"https://www.teahub.io/photos/full/105-1058135_free-music-wallpapers-for-android-guitar-wallpaper-hd.png"));
        CategoryRvModelArrayList.add(new categoryRvModel("Abstract" ,"https://w0.peakpx.com/wallpaper/380/696/HD-wallpaper-abstract-3d-beauty-blue-glod-s7.jpg"));
        CategoryRvModelArrayList.add(new categoryRvModel("Cars" ,"https://wallpapercave.com/wp/wp8879411.jpg"));
        CategoryRvModelArrayList.add(new categoryRvModel("Flowers" ,"https://w0.peakpx.com/wallpaper/821/401/HD-wallpaper-spring-flowers-flowers-spring-thumbnail.jpg"));
        CategoryRvAdapter.notifyDataSetChanged();


    }

    @Override
    public void onCategoryClick(int position) {
String category  = CategoryRvModelArrayList.get(position).getCategory();
getWallpapersBYCategory(category);

    }
}



















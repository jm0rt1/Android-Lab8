package com.example.lab8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            setSupportActionBar(findViewById(R.id.custom_toolbar));

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            recyclerView = findViewById(R.id.recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.scrollToPosition(0);
            recyclerView.setLayoutManager(layoutManager);

            RefreshRecyclerView();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }

//        refreshDB();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_button:
                try{
                    Intent intent = new Intent(getApplicationContext(), NewPostActivity.class);
                    startActivity(intent);
                } catch (Exception ex){
                    Log.e("favorites", ex.toString());
                }

                return true;
            case R.id.schedule:
                Schedulers.scheduleJob(getApplicationContext());
            case R.id.alarm:
                Alarm a = new Alarm();
                a.setQuickAlarm(getApplicationContext());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void RefreshRecyclerView(){
        new ServerInterface.Posts.Download(this, recyclerView).execute();
    }



//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void refreshDB()
//    {
//        String result = "";
//        try
//        {
//            ArrayList<String> names = ServerInterface.Posts.getTitles();
//            ArrayAdapter nameadapter = new ArrayAdapter(this, R.layout.list_view_item, names);
//            ListView listView = (ListView) findViewById(R.id.my_list_view);
//            listView.setAdapter(nameadapter);
//
//        }
//        catch(Exception ex)
//        {
//            Log.d("JSONObject", "You had an exception");
//            ex.printStackTrace();
//        }
//    }
}
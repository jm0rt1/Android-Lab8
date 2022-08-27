package com.example.lab8;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class ServerInterface {

    public static class Posts {
        private static String postsJsonCache = ""; // TODO: need to cache this in a better way for a bigger database
        private static long timeSinceLastUpdate = 0;

        private static class Keys {
            public static String TITLE ="title";
            public static String CONTENT = "content";
            public static String OWNER = "owner_id";
            public static String ID = "id";
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private static void  guard(){
            //Call first in every method

            if ((Objects.equals(postsJsonCache, "") || timeSinceLastUpdate == 0)||(timeSinceLastUpdate+(1000)*60 > Instant.now().toEpochMilli())){
                postsJsonCache = ServerCommands.downloadJSONUsingHTTPGetRequest(ServerCommands.Urls.POSTS);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public static ArrayList<String> getTitles() throws JSONException {
            guard();

            JSONArray jsonArray = new JSONArray(postsJsonCache);
            ArrayList<String> names = new ArrayList<String>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                names.add(obj.getString(Keys.TITLE));
            }
            return names;
        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public static ArrayList<String[]> getPosts() throws JSONException {

            guard();

            JSONArray jsonArray = new JSONArray(postsJsonCache);
            ArrayList<String[]> names = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                String[] pair = new String[3];
                JSONObject obj = jsonArray.getJSONObject(i);
                pair[0] = obj.getString(Keys.TITLE);
                pair[1] = obj.getString(Keys.CONTENT);
                pair[2] = obj.getString(Keys.ID);

                names.add(pair);

            }
            return names;
        }

        public static void sendPost(int owner_id,String title, String content,  Context c){




            if(title.equals(""))
            {
                Toast.makeText(c, "title field cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(content.equals(""))
            {
                Toast.makeText(c, "content field cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(!(owner_id>=1))
            {
                Toast.makeText(c, "owner id field not valid ="+String.valueOf(owner_id), Toast.LENGTH_SHORT).show();
            }
            else
            {
                JSONObject newPost = new JSONObject();
                try {
                    newPost.put(Keys.TITLE, title);
                    newPost.put(Keys.CONTENT, content);
                    newPost.put(Keys.OWNER, owner_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ServerCommands.sendHttpPostRequest(ServerCommands.Urls.POSTS_ADD, newPost);

            }
        }
        public static boolean deletePost(String id){
            boolean result = ServerCommands.sendHttpDeleteRequest(ServerCommands.Urls.POSTS_DELETE +"?id="+ id);
            return result;
        }
    }

}

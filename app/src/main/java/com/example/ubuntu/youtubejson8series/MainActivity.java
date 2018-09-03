package com.example.ubuntu.youtubejson8series;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ActorAdapter actorAdapter;
    ArrayList<Actor> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = findViewById(R.id.lv);
        arrayList = new ArrayList<Actor>();
       ActorAsynTask actorAsynTask =  new ActorAsynTask();
       actorAsynTask.execute();  //////executing async task class
    }

    public class ActorAsynTask extends AsyncTask<String, Void, Boolean>{
        String datas = "";
        String json_string;
        String json_url;
        @Override
        protected Boolean doInBackground(String... strings) {

            try{

                URL url = new URL(json_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while ((json_string = bufferedReader.readLine())!= null){

                    datas = datas + json_string; //take all json data values

                }


              Log.d("TAG", "show json data" + datas);

                JSONObject jsonObject = new JSONObject(datas); //json all data values is considered as json object
                JSONArray jsonArray = jsonObject.getJSONArray("data"); //json object vitra ko data array lai chai array suppose garincha.

                for(int i= 0; i<jsonArray.length(); i++) {
                    Actor actor = new Actor();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i); //json array vitra ko json data lincha.

                Log.i("TAG","loop" + i);

                actor.setName(jsonObject1.getString("name")); //temprorly store name form json into pojo class
                    actor.setCountry(jsonObject1.getString("country"));
                    actor.setCity(jsonObject1.getString("city"));
                    actor.setId(jsonObject1.getString("id"));
                    actor.setChildren(jsonObject1.getString("children"));
                    actor.setDescription(jsonObject1.getString("description"));
                    actor.setImage(jsonObject1.getString("image"));

                    arrayList.add(actor); //arraylist: list of array or array of array
//                                        //while running loop it keep on store object into arraylist.
                                        //{obj, obj1, obj2,....,}
                }
                return  true;

            }

            catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean = false){
                Log.d("TAG", "data was not parse");
            }else{
                actorAdapter = new ActorAdapter(getApplicationContext(),R.layout.row, arrayList);
                //send this values to the ActorAdapter constructor
                listView.setAdapter(actorAdapter);
            }
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onPreExecute() {
            json_url = "https://api.myjson.com/bins/lzlxw";
            super.onPreExecute();
        }
    }
}

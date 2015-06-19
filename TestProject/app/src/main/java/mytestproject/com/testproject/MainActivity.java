package mytestproject.com.testproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {

//    private SharedPreferences sharedprefs;
//    SharedPreferences.Editor editor;
//    public static final String PREFS_NAME = "MyPrefsFile";
//    private EditText name;
//    private TextView showNameText;
//    private Button saveName;

//    private Button playButton;
//    private MediaPlayer mediaPlayer;
//    private Button prevButton;
//    private Button nextButton;
//    private TextView text;

//    private ImageView thumbPrint;
//    private TextView result;
//    private Runnable mRunnable = null;
//    private Handler mHandler;
//    private String[] moodResults;
//    private  AnimationDrawable thumbAnimation;

    private EditText enterText;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesys);

        enterText = (EditText) findViewById(R.id.editText);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().equals("")){
                    //save the text to our file
                    Toast.makeText(getApplicationContext(), "Text saved!", Toast.LENGTH_LONG).toString();
                    String data = enterText.getText().toString();
                    writeToFile(data);
                }else {

                    Toast.makeText(getApplicationContext(), "Please enter text", Toast.LENGTH_LONG).toString();
                }
            }
        });

        if (readFromFile() != null){
            //then we know that there's something to read, so populate our textView
            enterText.setText(readFromFile());
        }else {
            enterText.setHint("");
        }


//        moodResults = new String[] {
//                "Someone is cranky!",
//                "You are my sunshine!",
//                "No comments...",
//                "You're stressed out!",
//                "Happy camper :)",
//                "Not your day :(",
//                "Smile - it's good for you.",
//                "In the clouds...",
//                "Pensive!",
//                "Sad!",
//                "Excited!"
//
//        };
//
//
//        thumbPrint = (ImageView) findViewById(R.id.thumbPrint);
//
//        thumbPrint.setBackgroundResource(R.drawable.thumb_animation);
//        thumbAnimation = (AnimationDrawable) thumbPrint.getBackground();
//
//
//        result = (TextView) findViewById(R.id.resultText);
//
//        thumbPrint.setOnLongClickListener(new View.OnLongClickListener() {
//
//
//            @Override
//            public boolean onLongClick(View v) {
//                //Do scan animation and then show result
//                //start animation
//                thumbAnimation.start();
//
//                showResult();
//
//
//                return true;
//            }
//        });
   }

    public void writeToFile(String myData){

        try{

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("diary.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(myData);
            outputStreamWriter.close(); //always close the stream once you are done!

        }catch (IOException e ){
            //in case things don't work out as intended.
            Log.v("MainActivity", "File write failed: " + e.toString());

        }
    }

    private String readFromFile(){


        String result = "";

        try {
            InputStream inputStream = openFileInput("diary.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (tempString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(tempString);
                }

                inputStream.close();
                result = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("MainActivity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("MainActivity", "Can not read file: " + e.toString());
        }

        return result;
//
//        String result = ""; //will hold our returned data (string/text)
//
//        try{
//
//            InputStream inputStream = openFileInput("myDiary.txt"); //getting our file and put it as an input stream.
//
//            if( inputStream != null){
//
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String stringHolder = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                stringHolder = bufferedReader.readLine();
//
//                while( stringHolder != null ){
//                    stringBuilder.append(stringHolder);
//                }
//
//                inputStream.close();
//
//                result = stringBuilder.toString();
//
//            }
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
    }

//    public void showResult(){
//
//          mRunnable = new Runnable() {
//              @Override
//              public void run() {
//                  int min = 0;
//                  int max = moodResults.length;
//
//                  int rand = (int) (Math.random() * moodResults.length);
//
//                  //int randNumber = rand.nextInt(max - min + 1) + moodResults.length;
//                  //Toast.makeText(getApplicationContext(), String.valueOf(rand), Toast.LENGTH_LONG).show();
//                  result.setText(moodResults[rand]);
//
//                  //stop animation
//                  thumbAnimation.stop();
//
//                  //set thumbnail background back to just green
//                  //thumbPrint.setBackgroundResource(R.drawable.thumb_finger_prints);
//              }
//          };
//
//        android.os.Handler mHandler = new android.os.Handler();
//        mHandler.postDelayed(mRunnable, 5000);
//    }


//        mediaPlayer = new MediaPlayer();
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_field);
//
//
//        text = (TextView) findViewById(R.id.artistName);
//
//
//
//
//        playButton = (Button) findViewById(R.id.playButton);
//        prevButton = (Button) findViewById(R.id.prevButton);
//        nextButton = (Button) findViewById(R.id.nextButton);


//        playButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
//        prevButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_previous));
//        nextButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_next));
//

        //set up the icon buttons

//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (mediaPlayer.isPlaying()){
//                    pauseMusic();
//                }else {
//                    playMusic();
//                }
//
//            }
//        });
//
//        prevButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer.isPlaying()){
//                    //prevMusic();
//                } else {
//                    //playMusic();
//                }
//            }
//        });
//
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer.isPlaying()){
//                   // nextMusic();
//                }else {
//                   // playMusic();
//                }
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//        name = (EditText) findViewById(R.id.nameEditText);
//        showNameText = (TextView) findViewById(R.id.showMyNameTxtView);
//
//        saveName = (Button) findViewById(R.id.saveButton);
//        saveName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, 0);
//                SharedPreferences.Editor editor = myPrefs.edit();
//
//                editor.putString("name", name.getText().toString());
//                editor.commit();
//
//
//            }
//        });
//
//
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
//
//        if (prefs.contains("name")) {
//            String userName = prefs.getString("name", "not found");
//            showNameText.setText("You're " + userName);
//        } else {
//            showNameText.setText("");
//        }
//    }

//
//
//
//
//
//    }
//
//
//    public void playMusic(){
//        if (mediaPlayer != null){
//            mediaPlayer.start();
//
//            text.setText("Music Playing now...");
//            playButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
//        }
//    }
//
//    public void pauseMusic(){
//        if (mediaPlayer != null){
//            mediaPlayer.pause();
//            text.setText("Music Paused!");
//            playButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
//        }
//    }
//
//    public void prevMusic(){
//         if (mediaPlayer != null){
//            // text.setText("Music Restarting!");
//             //mediaPlayer.reset(); // reset also,
//             //playButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
//
//         }
//    }
//
//    public void nextMusic(){
//        if (mediaPlayer != null){
//            //text.setText("Music Restarting!");
//            //mediaPlayer.reset(); // reset also,
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (mediaPlayer != null && mediaPlayer.isPlaying()){
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//        super.onDestroy();
//    }

}

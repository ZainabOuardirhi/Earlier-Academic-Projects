package org.example.riddlegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;

/** Demonstration reconstruction based on 2020 report screenshots. NO live Watson integration. */
public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
    private static final int CAPTURE = 17;
    private final String[] clues = {"Find something green", "Find a building", "Find something red", "Find a flower", "Find something round"};
    private int level = -1;
    private TextToSpeech speaker;
    private TextView heading, detail;
    private ImageView photo;
    private Button action, retake;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_main);
        heading = findViewById(R.id.heading); detail = findViewById(R.id.detail);
        photo = findViewById(R.id.photo); action = findViewById(R.id.action); retake = findViewById(R.id.retake);
        speaker = new TextToSpeech(this,this); welcome();
    }
    private void welcome() {
        heading.setText("Welcome to the Riddle Game!");
        detail.setText("Five riddles. Capture images as answers.\n\nThis reproduction uses a manual verification button, not IBM Watson Visual Recognition.");
        photo.setVisibility(View.GONE); retake.setVisibility(View.GONE); action.setText("Start");
        action.setOnClickListener(v -> { level=0; showClue(); });
    }
    private void showClue() {
        photo.setVisibility(View.GONE); retake.setVisibility(View.GONE);
        heading.setText("Riddle " + (level+1) + " of " + clues.length);
        detail.setText(clues[level]); say(clues[level]);
        action.setText("Capture photo"); action.setOnClickListener(v -> {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager())!=null) startActivityForResult(intent,CAPTURE);
            else detail.setText("Camera app unavailable. You can skip to the next riddle in this demo.");
        });
    }
    @Override protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CAPTURE && resultCode==RESULT_OK && data!=null) {
            Object image=data.getExtras()==null?null:data.getExtras().get("data");
            if(image instanceof Bitmap) { photo.setImageBitmap((Bitmap)image); photo.setVisibility(View.VISIBLE); }
            detail.setText("Photo captured. Original project used IBM Watson Visual Recognition.\nThis reconstruction cannot determine whether the picture is correct.");
            action.setText("Mark as correct (demo)");
            action.setOnClickListener(v -> nextRiddle());
            retake.setVisibility(View.VISIBLE); retake.setText("Retake"); retake.setOnClickListener(v -> showClue());
        }
    }
    private void nextRiddle() {
        level++; if(level<clues.length) showClue(); else {
            heading.setText("Well done!"); detail.setText("You are the master of the Riddle Game!");
            photo.setVisibility(View.GONE); retake.setVisibility(View.GONE); action.setText("Play again");
            action.setOnClickListener(v -> welcome());
        }
    }
    private void say(String text) { if(speaker!=null) speaker.speak(text,TextToSpeech.QUEUE_FLUSH,null,"clue"); }
    @Override public void onInit(int status) { if(status==TextToSpeech.SUCCESS) speaker.setLanguage(Locale.ENGLISH); }
    @Override protected void onDestroy() { if(speaker!=null) {speaker.stop();speaker.shutdown();} super.onDestroy(); }
}

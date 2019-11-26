package com.example.gym_inclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gym_inclass.DB.AppDatabase;
import com.example.gym_inclass.DB.GymLogDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {
        TextView mMainDisplay;
        EditText exercise;
        EditText weight;
        EditText reps;
        Button mSubmit;
        GymLogDAO mGymLogDAO;
        List<GymLog> mGymLogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainDisplay = findViewById(R.id.mainGymLogDisplay);
        mMainDisplay.setMovementMethod(new ScrollingMovementMethod());
        exercise = findViewById(R.id.mainExerciseEditText);
        weight = findViewById(R.id.mainWeightEditText);
        reps = findViewById(R.id.mainRepsEditText);
        mSubmit = findViewById(R.id.mainSubmitButton);
        mGymLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getGymLogDAO();
        refreshDisplay();
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitGymLog();
                refreshDisplay();
            }
        });
    }
    private void refreshDisplay(){
        mGymLogs = mGymLogDAO.getGymLogs();
        if(!mGymLogs.isEmpty()){
            mMainDisplay.setText("");
            for(GymLog log: mGymLogs){
                String temp = mMainDisplay.getText().toString();
                mMainDisplay.setText(log.toString());
                mMainDisplay.append(temp);
            }
        }else{
            mMainDisplay.setText("No logs yet, time to hit the gym!");
        }
    }
    private void submitGymLog(){
        String mExercise = exercise.getText().toString();
        int mWeight = Integer.parseInt(weight.getText().toString());
        int mReps = Integer.parseInt(reps.getText().toString());
        mGymLogDAO.insert( new GymLog(mExercise,mWeight,mReps));
    }
}

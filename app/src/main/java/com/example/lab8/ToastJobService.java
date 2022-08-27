package com.example.lab8;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class ToastJobService extends JobService {


    @Override
    public boolean onStartJob (JobParameters jobParameters) {
        Toast.makeText(getApplicationContext(), "Executing the Job", Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public boolean onStopJob (JobParameters jobParameters) { return true; }
}



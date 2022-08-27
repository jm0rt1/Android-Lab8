package com.example.lab8;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Schedulers {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleJob(Context context){
        ComponentName serviceComponent = new ComponentName(context, ToastJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder( 0, serviceComponent);
        builder.setMinimumLatency(1000);
        builder.setOverrideDeadline(3000);
        JobScheduler jobScheduler= context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }
}

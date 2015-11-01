package com.byan.apaja.main.app.ui.trainschedule;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byan.apaja.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TrainScheduleActivityFragment extends Fragment {

    public TrainScheduleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_train_schedule, container, false);
    }
}

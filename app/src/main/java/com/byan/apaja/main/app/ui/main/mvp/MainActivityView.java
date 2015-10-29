package com.byan.apaja.main.app.ui.main.mvp;

/**
 * Created by byan on 10/27/2015.
 */
public interface MainActivityView {
    public enum ViewState{
        INIT, START_TRANSITION, END_TRANSITION
    }

    public void showState(ViewState state);
}

package com.byan.apaja.main.main.mainmapfragment.mvp;

/**
 * Created by byan on 10/27/2015.
 */
public interface MainMapFragmentView {

    public enum ViewState{
        LOAD_MAPS, SUCCESS, FAILURE, LOADING
    }

    public void showState(ViewState state);
}

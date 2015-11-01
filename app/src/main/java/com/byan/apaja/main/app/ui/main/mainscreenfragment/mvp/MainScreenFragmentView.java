package com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp;

/**
 * Created by byan on 10/27/2015.
 */
public interface MainScreenFragmentView {

    public enum ViewState{
        INIT_STATE, VIEW_SEARCH_STATE, INPUT_SEARCH_STATE, POSTING_SEARCH_STATE,
        RENDER_ROUTE_STATE, SHARE_STATE, REPORT_STATE
    }

    public void showState(ViewState state);
}

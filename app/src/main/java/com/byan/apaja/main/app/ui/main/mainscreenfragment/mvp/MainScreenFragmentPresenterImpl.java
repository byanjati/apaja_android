package com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp;

import com.byan.apaja.main.app.ui.main.mainscreenfragment.MainScreenFragment;

/**
 * Created by byan on 10/27/2015.
 */
public class MainScreenFragmentPresenterImpl implements MainScreenFragmentPresenter {

    public MainScreenFragmentView mainScreenFragmentView;
    public MainScreenFragmentInteractorImpl mainScreenFragmentInteractor;

    public MainScreenFragmentPresenterImpl(MainScreenFragmentView mainScreenFragmentView){
        this.mainScreenFragmentView = mainScreenFragmentView;
    }

    @Override
    public void presentState(MainScreenFragmentView.ViewState state) {
        /* presenter memanggil view, barangkali ada tambahan model dan posting API interactor*/
        switch(state){
            case INIT_STATE:
                mainScreenFragmentView.showState(MainScreenFragmentView.ViewState.INIT_STATE);
                break;
            case VIEW_SEARCH_STATE:
                mainScreenFragmentView.showState(MainScreenFragmentView.ViewState.VIEW_SEARCH_STATE);
                break;
            case INPUT_SEARCH_STATE:
                mainScreenFragmentView.showState(MainScreenFragmentView.ViewState.INPUT_SEARCH_STATE);
                break;
            case POSTING_SEARCH_STATE:
                mainScreenFragmentView.showState(MainScreenFragment.ViewState.POSTING_SEARCH_STATE);
                break;
            case RENDER_ROUTE_STATE:
                mainScreenFragmentView.showState(MainScreenFragment.ViewState.RENDER_ROUTE_STATE);
                break;
        }
        mainScreenFragmentView.showState(state);
    }
}

package com.byan.apaja.main.app.ui.main.mvp;

/**
 * Created by byan on 10/27/2015.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter{

    private MainActivityView view;

    public MainActivityPresenterImpl(MainActivityView view){
        this.view = view;
    }

    @Override
    public void presentState(MainActivityView.ViewState state) {
        switch (state){
            case INIT:
                view.showState(MainActivityView.ViewState.INIT);
                break;
            case START_TRANSITION:
                view.showState(MainActivityView.ViewState.START_TRANSITION);
                break;
            case END_TRANSITION:
                view.showState(MainActivityView.ViewState.END_TRANSITION);
                break;
        }
    }
}

package com.byan.apaja.main.app.ui.main.mainscreenfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.byan.apaja.R;
import com.byan.apaja.main.app.ui.main.MainActivity;
import com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp.MainScreenFragmentPresenter;
import com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp.MainScreenFragmentPresenterImpl;
import com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp.MainScreenFragmentView;
import com.byan.apaja.main.app.ui.main.mainscreenfragment.mvp.MainScreenModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainScreenFragment extends Fragment
        implements MainScreenFragmentView {

    public MainScreenFragmentPresenter presenter;

    public MainScreenFragment() {
        // Required empty public constructor
    }

    Toolbar toolbar_init_state;
    Toolbar toolbar_search_state;
    Toolbar toolbar_input_state;
    View toolbar_init;
    View toolbar_search;
    View toolbar_input;

    EditText fromLocationEditText;
    EditText toLocationEditText;
    TextView fromLocation;
    TextView toLocation;

    MainScreenModel model;

    @Bind(R.id.init_state) FrameLayout viewInitState;
    @Bind(R.id.input_location_state) FrameLayout viewInputState;

    final String TAG = "PathGoogleMapActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainScreenFragmentPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_map, container,
                false);

        ButterKnife.bind(this, v);
        ((MainActivity) v.getContext()).setMainScreenFragment(this);
        bindingViews();
        setupOnClickListener();
        presenter.presentState(ViewState.INIT_STATE);

        return v;
    }

    private void bindingViews(){
        toolbar_init = viewInitState.findViewById(R.id.toolbar_init);
        toolbar_search = viewInitState.findViewById(R.id.toolbar_search_state);
        toolbar_input = viewInputState.findViewById(R.id.toolbar_input_search);

        fromLocation = (TextView) toolbar_search.findViewById(R.id.from_location);
        toLocation = (TextView) toolbar_search.findViewById(R.id.to_location);

        toolbar_init_state = (Toolbar) toolbar_init.findViewById(R.id.appbar_toolbar_init);
        toolbar_search_state = (Toolbar) toolbar_search.findViewById(R.id.appbar_toolbar_search_state);
        toolbar_input_state = (Toolbar) toolbar_input.findViewById(R.id.appbar_toolbar_input_search);
        toolbar_input_state.inflateMenu(R.menu.search_menu);

        toolbar_search_state.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar_input_state.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        fromLocationEditText = (EditText) toolbar_input_state.findViewById(R.id.input_from_location);
        toLocationEditText = (EditText) toolbar_input_state.findViewById(R.id.input_to_location);
    }

    private void setupOnClickListener(){
        toolbar_search_state.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar_search.setVisibility(View.GONE);
                toolbar_init.setVisibility(View.VISIBLE);
            }
        });

        toolbar_input_state.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewInputState.setVisibility(View.GONE);
                presenter.presentState(ViewState.VIEW_SEARCH_STATE);
            }
        });

        fromLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.presentState(ViewState.INPUT_SEARCH_STATE);
                toLocationEditText.setVisibility(View.GONE);
                fromLocationEditText.setVisibility(View.VISIBLE);
            }
        });

        toLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.presentState(ViewState.INPUT_SEARCH_STATE);
                fromLocationEditText.setVisibility(View.GONE);
                toLocationEditText.setVisibility(View.VISIBLE);
            }
        });

        fromLocationEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    fromLocation.setText(textView.getText().toString());
                    return true;
                }
                return false;
            }
        });

        toLocationEditText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    toLocation.setText(textView.getText().toString());
                    return true;
                }
                return false;
            }
        });

        toolbar_input_state.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.clear_text:
                        if(fromLocationEditText.getVisibility()==View.VISIBLE) {
                            fromLocationEditText.setText("");
                        }else{
                            toLocationEditText.setText("");
                        }
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showState(ViewState state) {
        switch (state){
            case INIT_STATE:
                viewInitState.setVisibility(View.VISIBLE);
                toolbar_init.setVisibility(View.VISIBLE);
                break;
            case VIEW_SEARCH_STATE:
                viewInitState.setVisibility(View.VISIBLE);
                toolbar_init.setVisibility(View.GONE);
                toolbar_search.setVisibility(View.VISIBLE);
                break;
            case INPUT_SEARCH_STATE:
                viewInitState.setVisibility(View.GONE);
                viewInputState.setVisibility(View.VISIBLE);
                break;
            case POSTING_SEARCH_STATE:
                break;
            case RENDER_ROUTE_STATE:
                break;
        }
    }

}

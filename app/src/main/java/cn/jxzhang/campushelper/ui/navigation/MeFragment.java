package cn.jxzhang.campushelper.ui.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.jxzhang.campushelper.R;

public class MeFragment extends Fragment {
    private static final String PAGE_INDEX = "index";

    private int index;

    public MeFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index index.
     * @return A new instance of fragment MeFragment.
     */
    public static MeFragment newInstance(int index) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(PAGE_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, inflate);
        // Inflate the layout for this fragment
        return inflate;
    }

    public void actionAccount() {

    }

    public void actionSetting(){

    }

    public void actionUpdateLog(){

    }

    public void actionSwitchAccount(){

    }

    public void actionAbout(){

    }

    public void actionHelpAndFeedback(){

    }

    public void actionExist(){

    }
}

package cn.jxzhang.campushelper.ui.navigation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.ui.query.CETActivity;
import cn.jxzhang.campushelper.ui.setting.AboutActivity;
import cn.jxzhang.campushelper.util.TextUtils;
import cn.jxzhang.campushelper.util.ToastUtils;

public class DiscoverFragment extends Fragment {
    private static final String PAGE_INDEX = "index";

    private SharedPreferences preference;

    private int index;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index index.
     * @return A new instance of fragment DiscoverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverFragment newInstance(int index) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = this.getActivity().getPreferences(Activity.MODE_PRIVATE);
        if (getArguments() != null) {
            index = getArguments().getInt(PAGE_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.action_cet)
    public void actionCet(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), CETActivity.class);
        this.getActivity().startActivity(intent);
    }

    @OnClick(R.id.action_standard_chinese)
    public void actionStandardChinese(){
        ToastUtils.toast(this.getActivity(),"非查询时间！");
    }

    @OnClick(R.id.action_time_table)
    public void actionTimeTable(){
        ToastUtils.toast(this.getActivity(),"教务网暂未开放外网链接！");
    }

    @OnClick(R.id.action_grades)
    public void actionGrades(){
        ToastUtils.toast(this.getActivity(),"教务网暂未开放外网链接！");
    }

    @OnClick(R.id.action_library)
    public void actionLibrary(){
        ToastUtils.toast(this.getActivity(),"教务网暂未开放外网链接！");
    }

    @OnClick(R.id.action_computer)
    public void actionComputer(){
        ToastUtils.toast(this.getActivity(),"非查询时间！");
    }

    @OnClick(R.id.action_check_in)
    public void actionCheckIn(){
        String lastCheckInTime = preference.getString("today", "");
        DateTime dateTime = DateTime.now();
        String today = dateTime.toString("yyyyMMdd");
        if (TextUtils.isEmpty(lastCheckInTime)) {
            ToastUtils.toast(this.getActivity(), "签到成功");
            saveToday(today);
        } else if (!today.equals(lastCheckInTime)) {
            ToastUtils.toast(this.getActivity(), "签到成功");
            saveToday(today);
        } else {
            ToastUtils.toast(this.getActivity(), "您今天已经签到过啦，不用再签啦！");
        }
    }

    private void saveToday(String today){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("today", today);
        editor.apply();
    }
}

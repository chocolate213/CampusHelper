package cn.jxzhang.campushelper.ui.navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.open.Constants;
import cn.jxzhang.campushelper.ui.setting.AboutActivity;
import cn.jxzhang.campushelper.ui.setting.LogActivity;
import cn.jxzhang.campushelper.ui.setting.PersonalInfoActivity;
import cn.jxzhang.campushelper.ui.setting.SettingActivity;

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

    @OnClick(R.id.action_setting)
    public void actionSetting(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), SettingActivity.class);
        this.getActivity().startActivity(intent);
    }

    @OnClick(R.id.action_update_log)
    public void actionUpdateLog(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), LogActivity.class);
        this.getActivity().startActivity(intent);
    }

    @OnClick(R.id.account_info)
    public void actionPersonalInfo(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), PersonalInfoActivity.class);
        this.getActivity().startActivity(intent);
    }

    @OnClick(R.id.action_switch_account)
    public void actionSwitchAccount(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());

        dialog.setTitle("切换账户");
        dialog.setMessage("切换账号并返回登陆界面？");
        dialog.setCancelable(false);
        dialog.setNegativeButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MeFragment.this.getActivity().finish();
            }
        });

        dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    @OnClick(R.id.action_about)
    public void actionAbout(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), AboutActivity.class);
        this.getActivity().startActivity(intent);
    }

    @OnClick(R.id.text_finish_all_activity)
    public void actionExist(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());

        dialog.setTitle("退出");
        dialog.setMessage("确认退出？");
        dialog.setCancelable(false);
        dialog.setNegativeButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MeFragment.this.getActivity().finish();
            }
        });

        dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}

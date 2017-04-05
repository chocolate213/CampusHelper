package cn.jxzhang.campushelper.ui.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.adapter.ContactAdapter;
import cn.jxzhang.campushelper.model.Contact;
import cn.jxzhang.campushelper.model.ContactComparator;
import cn.jxzhang.campushelper.util.SimplifiedChineseUtils;
import cn.jxzhang.campushelper.view.SideBarView;

public class ContactFragment extends Fragment implements SideBarView.LetterSelectListener {

    private static final String PAGE_INDEX = "index";

    private int index;

    @BindView(R.id.contact_list)
    ListView mContactList;

    @BindView(R.id.contact_sidebar)
    SideBarView mSideBarView;

    @BindView(R.id.contact_tip)
    TextView mContactTip;

    private ContactAdapter contactAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index index.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(int index) {
        ContactFragment fragment = new ContactFragment();
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

    private void init() {
        String[] contactsArray = getResources().getStringArray(R.array.data);
        String[] headArray = getResources().getStringArray(R.array.head);

        ArrayList<Contact> contacts = new ArrayList<>();
        for (String aContactsArray : contactsArray) {
            Contact user = new Contact();
            user.setName(aContactsArray);
            String firstSpell = SimplifiedChineseUtils.firstSpell(aContactsArray);
            String substring = firstSpell.substring(0, 1).toUpperCase();
            if (substring.matches("[A-Z]")) {
                user.setLetter(substring);
            } else {
                user.setLetter("#");
            }
            contacts.add(user);
        }

        for (String aHeadArray : headArray) {
            Contact user = new Contact();
            user.setName(aHeadArray);
            user.setLetter("@");
            contacts.add(user);
        }



        Collections.sort(contacts, new ContactComparator());

        contactAdapter = new ContactAdapter(getActivity().getApplication());
        contactAdapter.setData(contacts);
        mContactList.setAdapter(contactAdapter);

        mSideBarView.setOnLetterSelectListen(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }

    @Override
    public void onLetterSelected(String letter) {
        setListViewPosition(letter);
        mContactTip.setText(letter);
        mContactTip.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLetterChanged(String letter) {
        setListViewPosition(letter);
        mContactTip.setText(letter);
    }

    @Override
    public void onLetterReleased(String letter) {
        mContactTip.setVisibility(View.GONE);
    }

    private void setListViewPosition(String letter){
        int firstLetterPosition = contactAdapter.getFirstLetterPosition(letter);
        if(firstLetterPosition != -1){
            mContactList.setSelection(firstLetterPosition);
        }
    }
}

package cn.jxzhang.campushelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.model.Contact;
import cn.jxzhang.campushelper.util.SimplifiedChineseUtils;

/**
 * Created on 2017-04-04 22:01
 * <p>Title:       ContactAdapter</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context) {
        this.mContext = context;
        contacts = new ArrayList<>();
    }

    public void setData(List<Contact> data) {
        this.contacts.clear();
        this.contacts.addAll(data);
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_item, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.contact_category);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.contact_name);
            viewHolder.tvItem = (LinearLayout) convertView.findViewById(R.id.contact_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(contacts.get(position).getName());
        viewHolder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, contacts.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        if (position == getFirstLetterPosition(position) && !contacts.get(position).getLetter().equals("@")) {
            viewHolder.tvTitle.setVisibility(View.VISIBLE);
            viewHolder.tvTitle.setText(contacts.get(position).getLetter().toUpperCase());
        } else {
            viewHolder.tvTitle.setVisibility(View.GONE);
        }
        return convertView;
    }

    /**
     * 顺序遍历所有元素．找到position对应的title是什么（A,B,C?）然后找这个title下的第一个item对应的position
     *
     * @param position
     * @return
     */
    private int getFirstLetterPosition(int position) {

        String letter = contacts.get(position).getLetter();
        int cnAscii = SimplifiedChineseUtils.getCnAscii(letter.toUpperCase().charAt(0));
        int size = contacts.size();
        for (int i = 0; i < size; i++) {
            if (cnAscii == contacts.get(i).getLetter().charAt(0)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 顺序遍历所有元素．找到letter下的第一个item对应的position
     *
     * @param letter
     * @return
     */
    public int getFirstLetterPosition(String letter) {
        int size = contacts.size();
        for (int i = 0; i < size; i++) {
            if (letter.charAt(0) == contacts.get(i).getLetter().charAt(0)) {
                return i;
            }
        }
        return -1;
    }

    private class ViewHolder {
        TextView tvName;
        TextView tvTitle;
        LinearLayout tvItem;
    }
}

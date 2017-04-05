package cn.jxzhang.campushelper.model;

import java.util.Comparator;

/**
 * Created on 2017-04-04 22:59
 * <p>Title:       ContactComparator</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact contact1, Contact contact2) {
        if(contact1.getLetter().equals("@") || contact2.getLetter().equals("@")){
            return contact1.getLetter().equals("@") ? -1:1;
        }
        else if(!contact1.getLetter().matches("[A-z]+")){
            return 1;
        }else if(!contact2.getLetter().matches("[A-z]+")){
            return -1;
        }else {
            return contact1.getLetter().compareTo(contact2.getLetter());
        }
    }
}

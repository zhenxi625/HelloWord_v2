package com.demo.cc.service;

import com.demo.cc.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenXingLing on 2016/12/23.
 */

public class ContactService {

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(12, "用户1", "18363018876", 13003));
        contacts.add(new Contact(23, "用户2", "130066006", 122003));
        contacts.add(new Contact(98, "用户3", "186768768", 10988787));
        contacts.add(new Contact(76, "用户4", "1565622566", 1666));
        contacts.add(new Contact(34, "用户5", "1641682146", 148916));
        return contacts;
    }
}

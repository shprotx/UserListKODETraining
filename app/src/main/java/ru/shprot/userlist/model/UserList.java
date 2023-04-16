package ru.shprot.userlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {

    @SerializedName("items")
    private List<User> list;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}

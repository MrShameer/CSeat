package com.example.cseat;

import android.graphics.Bitmap;

public class UserData {
    private static UserData _instance;
    public static UserData getInstance() {
        {
            if (_instance == null)
            {
                _instance = new UserData();
            }
            return _instance;
        }
    }

    String uname = new String(), email = new String(), phone = new String();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url = new String();

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap bitmap = null;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

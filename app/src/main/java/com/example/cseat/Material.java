package com.example.cseat;

import android.net.Uri;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Material {
    private static Material _instance;
    public static Material getInstance(){
        {
            if (_instance == null) {
                _instance = new Material();
            }
            return _instance;
        }
    }

    List<String> url=new ArrayList<String>(),name = new ArrayList<String>();

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}

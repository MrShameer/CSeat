package com.example.cseat;

import java.util.ArrayList;
import java.util.List;

public class Vid {
    private static Vid _instance;
    public static Vid getInstance(){
        if (_instance == null)
        {
         _instance = new Vid();
        }
        return _instance;
    }

    List<VideoPlayer> allvideo=new ArrayList<VideoPlayer>();

    public List<VideoPlayer> getAllvideo() {
        return allvideo;
    }

    public void setAllvideo(List<VideoPlayer> allvideo) {
        this.allvideo = allvideo;
    }
}

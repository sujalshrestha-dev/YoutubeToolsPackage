package com.example.YoutubeTool.dtos;

import lombok.Data;

@Data
public class Thumbnails {
    public Thumbnail maxres;
    public Thumbnail high;
    public Thumbnail medium;
    public Thumbnail _default;

    public String getBestThumbnailUrl(){
        if(maxres!=null)  return maxres.url;
        if(high!=null) return high.url;
        if(medium!=null) return medium.url;
        return _default != null ? _default.url : "";
    }


}
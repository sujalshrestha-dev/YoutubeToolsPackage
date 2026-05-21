package com.example.YoutubeTool.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Snippet {
    public String title;
    public String description;
    public String channelTitle;
    public String publishedAt;
    public List<String> tags;
    public Thumbnails thumbnails;
}
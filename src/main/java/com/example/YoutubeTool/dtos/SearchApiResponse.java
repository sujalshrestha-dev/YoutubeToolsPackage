package com.example.YoutubeTool.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SearchApiResponse {
    public List<SearchItem> items;
}

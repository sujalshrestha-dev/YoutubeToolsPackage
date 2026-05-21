package com.example.YoutubeTool.Service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ThumbnailService {
    public String extractVideoId(String url) {
        if(url.matches("^[A-Za-z0-9_-]{11}$")){
            return url;
        }

        String[] patterns = {
                "(?:https?:\\/\\/)?(?:www\\.)?youtube\\.com\\/watch\\?v=([A-Za-z0-9_-]{11})",
                "(?:https?:\\/\\/)?(?:www\\.)?youtu\\.be\\/([A-Za-z0-9_-]{11})",
                "(?:https?:\\/\\/)?(?:www\\.)?youtube\\.com\\/embed\\/([A-Za-z0-9_-]{11})",
                "(?:https?:\\/\\/)?(?:www\\.)?youtube\\.com\\/shorts\\/([A-Za-z0-9_-]{11})"
        };

        for(String pattern : patterns){
            Matcher matcher = Pattern.compile(pattern).matcher(url);
            if(matcher.find()){
                return matcher.group(1);
            }
        }
        return null;
    }
}

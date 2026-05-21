package com.example.YoutubeTool.Controller;

import org.springframework.http.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.YoutubeTool.Service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThumbnailController {

    @Autowired
    ThumbnailService service;

    private static final String[] QUALITIES = {
            "maxresdefault.jpg",   // 1280x720 - not always available
            "sddefault.jpg",       // 640x480
            "hqdefault.jpg",       // 480x360 - almost always exists
            "mqdefault.jpg",       // 320x180
            "default.jpg"          // 120x90 - always exists
    };

    private String findBestThumbnailUrl(String videoId) throws Exception {
        for (String quality : QUALITIES) {
            String url = "https://img.youtube.com/vi/" + videoId + "/" + quality;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setConnectTimeout(3000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                return url;
            }
            conn.disconnect();
        }
        return null;
    }

    @GetMapping("/thumbnail")
    public String getThumbnail() {
        return "thumbnails";
    }

    @PostMapping("/get-thumbnail")
    public String showThumbnail(@RequestParam("videoUrlOrId") String videoUrlOrId, Model model) {
        String videoId = service.extractVideoId(videoUrlOrId);
        if (videoId == null) {
            model.addAttribute("error", "Invalid YouTube URL or ID.");
            return "thumbnails";
        }

        try {
            String thumbnailUrl = findBestThumbnailUrl(videoId);
            if (thumbnailUrl == null) {
                model.addAttribute("error", "No thumbnail found for this video.");
                return "thumbnails";
            }
            model.addAttribute("videoId", videoId);
            model.addAttribute("thumbnailUrl", thumbnailUrl);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch thumbnail: " + e.getMessage());
        }

        return "thumbnails";
    }

    @GetMapping("/download-thumbnail")
    public ResponseEntity<byte[]> downloadThumbnail(@RequestParam("videoId") String videoId) {
        try {
            String url = findBestThumbnailUrl(videoId);
            if (url == null) {
                return ResponseEntity.notFound().build();
            }

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            byte[] imageBytes = conn.getInputStream().readAllBytes();
            conn.disconnect();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDispositionFormData("attachment", "thumbnail-" + videoId + ".jpg");
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
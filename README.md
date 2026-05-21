# 🎬 YoutubeTools

> A lightweight Spring Boot web app that gives you SEO tags, thumbnail extraction, and full video details for any YouTube video — all in one place.

---

## ✨ Features

### 🏷️ SEO Tag Generator
- Enter a YouTube video title to search for similar videos
- Extracts and displays tags from the most relevant (primary) video
- Also shows tags from related videos for broader SEO coverage
- One-click **Copy Tags** button for instant use

### 🖼️ Thumbnail Extractor
- Paste any YouTube video URL or ID
- Fetches the highest quality thumbnail available
- Preview the thumbnail directly in the browser
- **Download** the thumbnail with a single click

### 📋 Video Data Retriever
- Fetches complete video metadata from the YouTube Data API v3
- Displays video title, channel name, publish date, full description, and all tags
- Clean, readable layout with dark mode support

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21+, Spring Boot |
| Templating | Thymeleaf |
| Styling | Tailwind CSS, Bootstrap Icons |
| API | YouTube Data API v3 |

---

## ⚙️ Setup & Installation

### Prerequisites
- Java 21+
- Maven
- A valid **YouTube Data API v3** key ([Get one here](https://console.cloud.google.com/))

### 1. Clone the repository

```bash
git clone https://github.com/your-username/YoutubeToolsPackage.git
cd YoutubeToolsPackage
```

### 2. Add your API key

Open `src/main/resources/application.properties` and add:

```properties
youtube.api.key=YOUR_YOUTUBE_API_KEY_HERE
```

### 3. Run the app

```bash
./mvnw spring-boot:run
```

Then open your browser at:

```
http://localhost:8080
```

---

## 📁 Project Structure

```
YoutubeToolsPackage/
├── src/
│   ├── main/
│   │   ├── java/com/example/YoutubeTool/
│   │   │   ├── Controller/
│   │   │   │   ├── PageController.java
│   │   │   │   ├── ThumbnailController.java
│   │   │   │   ├── YouTubeTagsController.java
│   │   │   │   └── YouTubeVideoController.java
│   │   │   ├── dtos/
│   │   │   │   ├── Id.java
│   │   │   │   ├── SearchApiResponse.java
│   │   │   │   ├── SearchItem.java
│   │   │   │   ├── Snippet.java
│   │   │   │   ├── Thumbnail.java
│   │   │   │   ├── Thumbnails.java
│   │   │   │   ├── VideoApiResponse.java
│   │   │   │   └── VideoItem.java
│   │   │   ├── Model/
│   │   │   │   ├── SearchVideo.java
│   │   │   │   ├── Video.java
│   │   │   │   └── VideoDetails.java
│   │   │   ├── Service/
│   │   │   │   ├── ThumbnailService.java
│   │   │   │   └── YouTubeService.java
│   │   │   └── YoutubeToolsApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       │   ├── fragments/
│   │       │   ├── home.html
│   │       │   ├── thumbnails.html
│   │       │   └── video-details.html
│   │       └── application.properties
│   └── test/
│       └── java/com/example/YoutubeTool/
│           └── YoutubeToolsPackageApplicationTests.java
├── .gitignore
├── .gitattributes
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---

> Built with ☕ Java & Spring Boot

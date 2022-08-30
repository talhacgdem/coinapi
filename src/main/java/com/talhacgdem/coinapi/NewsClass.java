/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talhacgdem.coinapi;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author talha
 */
public class NewsClass {

    private final String NEWSURL = "https://www.donanimhaber.com/kripto-para";

    private String new_CAPTION;
    private String new_DESCRIPTION;
    private String new_URL;
    private String new_IMAGE;
    private String new_DATE;

    Document newDoc;

    public JSONArray getAll() {

        JSONArray news = new JSONArray();
        JSONObject newelement;

        try {
            newDoc = Jsoup.connect(NEWSURL).ignoreContentType(true).get();

            Elements newEls = newDoc.select("div.tab-container div.heightWrapper.pageWrapper ul li");

            for (Element el : newEls) {
                new_CAPTION = el.select("div.govde h2 a.baslik.history-add").text();
                new_DESCRIPTION = el.select("div.govde div.aciklama").text();
                new_URL = el.select("div.govde h2 a.baslik.history-add").attr("href");
                new_IMAGE = el.select("figure").attr("style");
                new_DATE = el.select("div.govde div.bilgi span").attr("data-title");

                newelement = new JSONObject();
                newelement.put("caption", new_CAPTION);
                newelement.put("description", new_DESCRIPTION);
                newelement.put("url", "https://www.donanimhaber.com" + new_URL);
                newelement.put("image", extractUrls(new_IMAGE));
                newelement.put("date", new_DATE);
                news.put(newelement);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return news;
    }


    public static String extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        String url = containedUrls.get(0);
        url = url.substring(url.indexOf("&path=") + 6);
        return url;
    }

    public static void main(String[] args) {
        NewsClass n = new NewsClass();
//        String a = n.extractUrls("background-image:url('https://www.donanimhaber.com/cache-v2/?t=20220830125233&width=-1&text=0&path=https://www.donanimhaber.com/images/images/haber/152247/285xthodex-kurucusu-yakalandi.jpg'), url('/Content/img/_dummy_image.jpg');");
//        System.out.println(a);
        
        System.out.println(n.getAll());
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talhacgdem.coinapi;

/**
 *
 * @author talha
 */
public class News {

    private final String NEWSURL = "https://www.dunya.com/haberler/kripto-para/";

    private String new_CAPTION;
    private String new_DESCRIPTION;
    private String new_URL;
    private String new_IMAGE;

    Document newDoc;

    private void getAll() {
        int page = 1;

        JSONArray news = new JSONArray();
        JSONObject newelement = new JSONObject();

        while (true) {
            newDoc = Jsoup.connect(NEWSURL + page);
            if (newDoc.getStatus() == 404) {
                break;
            }

            
            Elements newAreas = newDoc.select("div.col-12.col-lg.mw0 div.row div.col-12.col-lg-4.d-xl-flex.d-lg-flex");
            
            System.out.println(newAreas.size());
            for(Element el : newAreas){
                System.out.println("-------------------------------\n" + el);
            }
            
//            new_CAPTION = "";
//            new_DESCRIPTION = "";
//            new_URL = "";
//            new_IMAGE = "";
//            
//            
//            
//            newelement = new JSONObject();
//            newelement.put("caption", new_CAPTION);
//            newelement.put("description", new_DESCRIPTION);
//            newelement.put("url", new_URL);
//            newelement.put("image", new_IMAGE);
//            news.put(newelement);

            page++;
        }

    }

    public static void main(String[] args) {
        News n = new News();
        n.getPageSize();
    }

}

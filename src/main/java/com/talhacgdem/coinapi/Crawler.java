/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talhacgdem.coinapi;
    
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author talha
 */
public class Crawler {


    private String logoSlugURL = "https://coinmarketcap.com/currencies/";
    private JSONArray resArray = new JSONArray();
    private JSONObject object;

    private Document coinmarket;
    private String rank;
    private String slug;
    private String name;
    private String subname;
    private String price;
    private String percent1h;
    private String percent24h;
    private String percent7d;
    private String marketCap;


    private Document docForLogo;
    private String getLogo(String slug){
        try{
            docForLogo = Jsoup.connect(logoSlugURL + slug + "/").get();
            String logoArea = docForLogo.select("div.nameHeader img").eq(0).attr("src");
            return logoArea;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getAll(String limit){


        try {

            String MAIN_URL = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=" + limit + "&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false";
            coinmarket = Jsoup.connect(MAIN_URL).ignoreContentType(true).get();

            JSONObject mainObj = new JSONObject(coinmarket.text());
            JSONArray cryptos = new JSONArray(mainObj.getJSONObject("data").getJSONArray("cryptoCurrencyList"));

            JSONObject crypto;
            JSONObject quotes;
            for (int i = 0; i < cryptos.length(); i++){
                crypto = cryptos.getJSONObject(i);
                quotes = crypto.getJSONArray("quotes").getJSONObject(0);

                rank = crypto.get("cmcRank").toString();
                name = crypto.get("name").toString();
                subname = crypto.get("symbol").toString();
                price = quotes.get("price").toString();
                percent1h = quotes.get("percentChange1h").toString();
                percent24h = quotes.get("percentChange24h").toString();
                percent7d = quotes.get("percentChange7d").toString();
                slug = crypto.get("slug").toString();

                object = new JSONObject();
                object.put("rank", rank);
                object.put("name", name);
                object.put("subname", subname);
                object.put("price", price);
                object.put("hourly", percent1h);
                object.put("daily", percent24h);
                object.put("weekly", percent7d);
                object.put("logo", getLogo(slug));

                resArray.put(object);
            }

            return resArray;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

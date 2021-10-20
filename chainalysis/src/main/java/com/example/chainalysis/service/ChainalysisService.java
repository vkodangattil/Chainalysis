package com.example.chainalysis.service;
import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChainalysisService {
    HttpClient client = HttpClient.newHttpClient();
    public Map<String, String> getCoinbasePrices() throws IOException, InterruptedException {
        Map<String, String> result = new HashMap<>();
        //Bitcoin
        HttpRequest buyRequest = HttpRequest.newBuilder(
                        URI.create("https://api.coinbase.com/v2/prices/BTC-USD/buy"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> buyResponse = client.send(buyRequest, HttpResponse.BodyHandlers.ofString());

        String buyResponseBody = buyResponse.body();
        Map<String, String> buyMap = new HashMap<String, String>();
        String[] buyPairs = buyResponseBody.split(",");
        for (int i=0;i<buyPairs.length;i++) {
            String pair = buyPairs[i];
            String[] keyValue = pair.split(":");
            buyMap.put(keyValue[0].replaceAll("^\"+|\"+$", ""), keyValue[1].replaceAll("[^0-9.]", ""));
        }

        HttpRequest sellRequest = HttpRequest.newBuilder(
                        URI.create("https://api.coinbase.com/v2/prices/BTC-USD/sell"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> sellResponse = client.send(sellRequest, HttpResponse.BodyHandlers.ofString());

        String sellResponseBody = sellResponse.body();
        Map<String, String> sellMap = new HashMap<String, String>();
        String[] sellPairs = sellResponseBody.split(",");
        for (int i=0;i<sellPairs.length;i++) {
            String pair = sellPairs[i];
            String[] keyValue = pair.split(":");
            sellMap.put(keyValue[0].replaceAll("^\"+|\"+$", ""), keyValue[1].replaceAll("[^0-9.]", ""));
        }
        result.put("bitcoinBuy", buyMap.get("amount"));
        result.put("bitcoinSell", sellMap.get("amount"));

        //Etheureum
        buyRequest = HttpRequest.newBuilder(
                        URI.create("https://api.coinbase.com/v2/prices/BTC-USD/buy"))
                .header("accept", "application/json")
                .build();

        buyResponse = client.send(buyRequest, HttpResponse.BodyHandlers.ofString());

        buyResponseBody = buyResponse.body();
        buyMap = new HashMap<String, String>();
        buyPairs = buyResponseBody.split(",");
        for (int i=0;i<buyPairs.length;i++) {
            String pair = buyPairs[i];
            String[] keyValue = pair.split(":");
            buyMap.put(keyValue[0].replaceAll("^\"+|\"+$", ""), keyValue[1].replaceAll("[^0-9.]", ""));
        }

        sellRequest = HttpRequest.newBuilder(
                        URI.create("https://api.coinbase.com/v2/prices/BTC-USD/sell"))
                .header("accept", "application/json")
                .build();

        sellResponse = client.send(sellRequest, HttpResponse.BodyHandlers.ofString());

        sellResponseBody = sellResponse.body();
        sellMap = new HashMap<String, String>();
        sellPairs = sellResponseBody.split(",");
        for (int i=0;i<sellPairs.length;i++) {
            String pair = sellPairs[i];
            String[] keyValue = pair.split(":");
            sellMap.put(keyValue[0].replaceAll("^\"+|\"+$", ""), keyValue[1].replaceAll("[^0-9.]", ""));
        }

        result.put("ethereumBuy", buyMap.get("amount"));
        result.put("ethereumSell", sellMap.get("amount"));

        return result;
    }

    public Map<String, String> getBinance() throws IOException, InterruptedException {
        Map<String, String> result = new HashMap<>();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create("https://api.binance.com/api/v3/ticker/24hr?symbol=BTCUSDT"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        Map<String, String> myMap = new HashMap<String, String>();
        String[] pairs = responseBody.split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            String key = keyValue[0].replaceAll("^\"+|\"+$", "");
            String temp = keyValue[1].replaceAll("[^0-9.]", "");
            if (key.equals("bidPrice") || key.equals("askPrice")) {
                double price = Double.parseDouble(temp);
                price = Math.round(price * 100.0) / 100.0;
                myMap.put(key, String.valueOf(price));
            }
        }
        result.put("bitcoinBuy", myMap.get("askPrice"));
        result.put("bitcoinSell", myMap.get("bidPrice"));

        //Etheureum
        request = HttpRequest.newBuilder(
                        URI.create("https://api.binance.com/api/v3/ticker/24hr?symbol=ETHUSDT"))
                .header("accept", "application/json")
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        responseBody = response.body();

        myMap = new HashMap<String, String>();
        pairs = responseBody.split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            String key = keyValue[0].replaceAll("^\"+|\"+$", "");
            String temp = keyValue[1].replaceAll("[^0-9.]", "");
            if (key.equals("bidPrice") || key.equals("askPrice")) {
                double price = Double.parseDouble(temp);
                price = Math.round(price * 100.0) / 100.0;
                myMap.put(key, String.valueOf(price));
            }
        }
        result.put("ethereumBuy", myMap.get("askPrice"));
        result.put("ethereumSell", myMap.get("bidPrice"));
        return result;
    }
}


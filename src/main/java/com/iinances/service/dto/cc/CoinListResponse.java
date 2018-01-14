package com.iinances.service.dto.cc;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CoinListResponse {

    @JsonProperty("Response")
    private String response;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("BaseImageUrl")
    private String baseImageUrl;
    @JsonProperty("BaseLinkUrl")
    private String baseLinkUrl;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Data")
    private Map<String, CoinResponse> coinMap;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public String getBaseLinkUrl() {
        return baseLinkUrl;
    }

    public void setBaseLinkUrl(String baseLinkUrl) {
        this.baseLinkUrl = baseLinkUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, CoinResponse> getCoinMap() {
        return coinMap;
    }

    public void setCoinMap(Map<String, CoinResponse> coinMap) {
        this.coinMap = coinMap;
    }


    @Override
    public String toString() {
        return "CoinListResponse{" +
                "response='" + response + '\'' +
                ", message='" + message + '\'' +
                ", baseImageUrl='" + baseImageUrl + '\'' +
                ", baseLinkUrl='" + baseLinkUrl + '\'' +
                ", type='" + type + '\'' +
                ", coinMap=" + coinMap +
                '}';
    }

}

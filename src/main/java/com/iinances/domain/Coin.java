package com.iinances.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Document(collection = "coin")
public class Coin {

    @Id
    private Long id;
    @NotNull
    @Field("url")
    private String url;
    @NotNull
    @Field("image_url")
    private String imageUrl;
    @NotNull
    @Field("name")
    private String name;
    @NotNull
    @Field("coin_name")
    private String coinName;
    @NotNull
    @Field("full_name")
    private String fullName;
    @NotNull
    @Field("algorithm")
    private String algorithm;
    @NotNull
    @Field("proof_type")
    private String proofType;
    @NotNull
    @Field("sort_order")
    private Long sortOrder;

    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", coinName='" + coinName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", algorithm='" + algorithm + '\'' +
                ", proofType='" + proofType + '\'' +
                ", sortOrder=" + sortOrder +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }
}

package com.example.hoc.houseofcode;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String auth_token;
    private String name;
    private String email;
    private String member_since;
    private String created_at;
    private String updated_at;
    private boolean has_access;
    private int subscription_id;
    private String subscription_expire;
    private String subscription_created_at;
    private String subscription_updated_at;
    private int oat_bran_id;
    private int oat_bran_amount;
    private String oat_bran_created_at;
    private String oat_bran_updated_at;

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getAuthToken(){
        return this.auth_token;
    }

    public void setAuthToken(String auth_token){
        this.auth_token = auth_token;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMemberSince(){
        return this.member_since;
    }

    public void setMemberSince(String member_since){
        this.member_since = member_since;
    }

    public String getCreatedAt(){
        return this.created_at;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    public String getUpdatedAt(){
        return this.updated_at;
    }

    public void setUpdatedAt(String updated_at){
        this.updated_at = updated_at;
    }

    public Boolean getHasAccess(){
        return this.has_access;
    }

    public void setHasAccess(Boolean has_access){
        this.has_access = has_access;
    }

    public int getSubscriptionID(){
        return this.subscription_id;
    }

    public void setSubscriptionID(int subscription_id){
        this.subscription_id = subscription_id;
    }

    public String getSubscriptionExpire(){
        return this.subscription_expire;
    }

    public void setSubscriptionExpire(String expire){
        this.subscription_expire = expire;
    }

    public String getSubscriptionCreatedAt(){
        return this.subscription_created_at;
    }

    public void setSubscriptionCreatedAt(String created_at){
        this.subscription_created_at = created_at;
    }

    public String getSubscriptionUpdatedAt(){
        return this.subscription_updated_at;
    }

    public void setSubscriptionUpdatedAt(String updated_at){
        this.subscription_updated_at = updated_at;
    }

    public int getOatBranID(){
        return this.oat_bran_id;
    }

    public void setOatBranID(int oat_bran_id){
        this.oat_bran_id = oat_bran_id;
    }

    public int getOatBranAmout(){
        return this.oat_bran_amount;
    }

    public void setOatBranAmount(int oat_bran_amount){
        this.oat_bran_amount= oat_bran_amount;
    }

    public String getOatBranCreatedAt(){
        return this.oat_bran_created_at;
    }

    public void setOatBranCreatedAt(String oat_bran_created_at){
        this.oat_bran_created_at = oat_bran_created_at;
    }

    public String getOatBranUpdatedAt(){
        return this.oat_bran_updated_at;
    }

    public void setOatBranUpdatedAt(String oat_bran_updated_at){
        this.oat_bran_updated_at = oat_bran_updated_at;
    }


}

package com.example.storemanger;

public class ModelUser {
    String storeName;
    String id;
    public ModelUser(String storeName,String id)
    {
        this.storeName=storeName;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

}

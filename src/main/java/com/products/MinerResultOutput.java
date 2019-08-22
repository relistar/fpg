package com.products;

public interface MinerResultOutput {
    void setData(Object object);

    String getFileName();

    String getStartMessage();

    String getSuccessMessage();

    String getContent();
}

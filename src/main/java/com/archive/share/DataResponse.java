package com.archive.share;

import java.util.List;

public class DataResponse<T> {
    List<T> data;

    public DataResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    
}

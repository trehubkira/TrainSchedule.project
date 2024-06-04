package com.example.servingwebcontent.forms;

import com.example.servingwebcontent.enums.TrainType;

public class TrainForm {
    private String code;
    private TrainType type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TrainType getType() {
        return type;
    }

    public void setType(TrainType type) {
        this.type = type;
    }
}

package com.example.servingwebcontent.models;

import com.example.servingwebcontent.enums.TrainType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Train {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private TrainType type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    private List<Route> routes;

    public Train(Long id, String code, Date date) {
        this.id = id;
        this.code = code;
    }

    public Train() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

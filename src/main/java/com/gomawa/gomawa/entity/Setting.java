package com.gomawa.gomawa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Setting {

    @Id @GeneratedValue
    @Column (name = "SETTING_ID")
    private long id;
}

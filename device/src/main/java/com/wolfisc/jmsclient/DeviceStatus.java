package com.wolfisc.jmsclient;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeviceStatus implements Serializable {

    private Integer id;
    private double temperature;
    private double humidity;
    private String timestamp;
}

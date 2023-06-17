package com.accounts.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//To load all the property I need from the giver configuration property file
@Configuration
@ConfigurationProperties(prefix = "accounts") //only handel those properties which starts with "accounts"
@Getter
@Setter
@ToString
public class TestShowingAllConfigData {
    private String msg;
    private String version;
    public void showProperties(){
        System.out.println(msg);
        System.out.println(version);

    }
}

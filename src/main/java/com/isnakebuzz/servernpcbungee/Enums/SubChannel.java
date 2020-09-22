package com.isnakebuzz.servernpcbungee.Enums;

import lombok.Getter;

@Getter
public enum SubChannel {

    COMMAND("command");

    private String name;

    SubChannel(String name) {
        this.name = name;
    }
}

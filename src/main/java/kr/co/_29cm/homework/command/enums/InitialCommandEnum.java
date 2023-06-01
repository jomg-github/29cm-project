package kr.co._29cm.homework.command.enums;

public enum InitialCommandEnum {
    ORDER("O"),
    QUIT("Q");

    String value;
    InitialCommandEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

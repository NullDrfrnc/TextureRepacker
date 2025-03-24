package com.nullptrexc.model.domain;

import java.util.Arrays;

public enum PackFormat {
    V_1("1.6.1", "1.8.9"),
    V_2("1.9", "1.10.2"),
    V_3("1.11", "1.12.2"),
    V_4("1.13", "1.14.4"),
    V_5("1.15", "1.16.1"),
    V_6("1.16.2", "1.16.5"),
    V_7("1.17", "1.17.1"),
    V_8("1.18", "1.18.2"),
    V_9("1.19", "1.19.2"),
    V_12("1.19.3"),
    V_13("1.19.4"),
    V_15("1.20", "1.20.1"),
    V_18("1.20.2"),
    V_22("1.20.3", "1.20.4"),
    V_32("1.20.5", "1.20.6"),
    V_34("1.21", "1.21.1"),
    V_42("1.21.2", "1.21.3"),
    V_46("1.21.4"),
    ;
    private final String firstVersion;
    private final String lastVersion;

    PackFormat(String firstVersion, String lastVersion) {
        this.firstVersion = firstVersion;
        this.lastVersion = lastVersion;
    }

    PackFormat(String version) {
        this(version, version);
    }

    public String getFirstVersion() {
        return firstVersion;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public boolean isInRange(String version) {
        return version.equals(firstVersion) || version.equals(lastVersion) || (version.compareTo(firstVersion) >= 0 && version.compareTo(lastVersion) <= 0);
    }

    public static PackFormat getFormatByVersion(String version) {
        return Arrays.stream(values()).filter(v -> v.isInRange(version)).findFirst().orElse(null);
    }
}

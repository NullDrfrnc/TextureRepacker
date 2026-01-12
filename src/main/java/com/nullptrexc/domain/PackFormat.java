package com.nullptrexc.domain;

import java.util.Arrays;

public enum PackFormat {
    V1(
            "1.6.1",
            "1.8.9",
            "1"
    ),
    V2(
            "1.9",
            "1.10.2",
            "2"
    ),
    V3(
            "1.11",
            "1.12.2",
            "3"
    ),
    V4(
            "1.13",
            "1.14.4",
            "4"
    ),
    V5(
            "1.15",
            "1.16.1",
            "5"
    ),
    V6(
            "1.16.2",
            "1.16.5",
            "6"
    ),
    V7(
            "1.17",
            "1.17.1",
            "7"
    ),
    V8(
            "1.18",
            "1.18.2",
            "8"
    ),
    V9(
            "1.19",
            "1.19.2",
            "9"
    ),
    V12(
            "1.19.3",
            "1.19.3",
            "12"
    ),
    V13(
            "1.19.4",
            "1.19.4",
            "13"
    ),
    V15(
            "1.20",
            "1.20.1",
            "15"
    ),
    V18(
            "1.20.2",
            "1.20.2",
            "18"
    ),
    V22(
            "1.20.3",
            "1.20.4",
            "22"
    ),
    V32(
            "1.20.5",
            "1.20.6",
            "32"
    ),
    V34(
            "1.21",
            "1.21.1",
            "34"
    ),
    V42(
            "1.21.2",
            "1.21.3",
            "42"
    ),
    V46(
            "1.21.4",
            "1.21.4",
            "46"
    ),
    V55(
            "1.21.5",
            "1.21.5",
            "55"
    ),
    V63(
            "1.21.6",
            "1.21.6",
            "63"
    ),
    V64(
            "1.21.7",
            "1.21.8",
            "64"
    ),
    V69( //nice
            "1.21.9",
            "1.21.10",
            "69"
    ),
    V75(
            "1.21.11",
            "1.21.11",
            "75"
    )
    ;

    private final MCVersion min;
    private final MCVersion max;
    private final String version;

    PackFormat(String min, String max, String version) {
        this.min = new MCVersion(min);
        this.max = new MCVersion(max);
        this.version = version;
    }

    public boolean isCompatible(MCVersion input) {
        return min.compareTo(input) <= 0 && max.compareTo(input) >= 0;
    }

    public static PackFormat findPackFormat(MCVersion input) {
        return Arrays.stream(PackFormat.values())
                .filter(p -> p.isCompatible(input))
                .findFirst()
                .orElse(null);
    }

    public static PackFormat findPackFormat(String version) {
        return Arrays.stream(PackFormat.values())
                .filter(p -> p.getVersion().equals(version))
                .findFirst()
                .orElse(null);
    }

    public MCVersion getMin() {
        return min;
    }

    public MCVersion getMax() {
        return max;
    }

    public String getVersion() {
        return version;
    }
}

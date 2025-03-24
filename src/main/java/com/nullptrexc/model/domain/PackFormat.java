package com.nullptrexc.model.domain;

import java.util.Arrays;

public enum PackFormat {
    V_1(1, "1.6.1", "1.8.9"),
    V_2(2, "1.9", "1.10.2"),
    V_3(3, "1.11", "1.12.2"),
    V_4(4, "1.13", "1.14.4"),
    V_5(5, "1.15", "1.16.1"),
    V_6(6, "1.16.2", "1.16.5"),
    V_7(7, "1.17", "1.17.1"),
    V_8(8, "1.18", "1.18.2"),
    V_9(9, "1.19", "1.19.2"),
    V_12(12, "1.19.3"),
    V_13(13, "1.19.4"),
    V_15(15, "1.20", "1.20.1"),
    V_18(18, "1.20.2"),
    V_22(22, "1.20.3", "1.20.4"),
    V_32(32, "1.20.5", "1.20.6"),
    V_34(34, "1.21", "1.21.1"),
    V_42(42, "1.21.2", "1.21.3"),
    V_46(46, "1.21.4");

    public final int packFormat;
    public final String firstVersion;
    public final String lastVersion;

    PackFormat(int packFormat, String firstVersion, String lastVersion) {
        this.packFormat = packFormat;
        this.firstVersion = firstVersion;
        this.lastVersion = lastVersion;
    }

    PackFormat(int packFormat, String version) {
        this(packFormat, version, version);
    }

    public boolean isInRange(String version) {
        return version.equals(firstVersion) || version.equals(lastVersion) || (version.compareTo(firstVersion) >= 0 && version.compareTo(lastVersion) <= 0);
    }

    /**
     * Retrieves the PackFormat enum value that corresponds to the specified version.
     *
     * @param version the version to check
     * @return the PackFormat enum value that includes the specified version in its range,
     *         or null if no such value exists
     */
    public static PackFormat getFormatByVersion(String version) {
        return Arrays.stream(values()).filter(v -> v.isInRange(version)).findFirst().orElse(null);
    }

    /**
     * Retrieves the PackFormat enum value that has a pack format greater than or equal to the specified pack format.
     *
     * @param packFormat the pack format to compare against
     * @return the PackFormat enum value with a pack format greater than or equal to the specified pack format,
     * or null if no such value exists
     */
    public static PackFormat getFormatByPackFormat(int packFormat) {
        return Arrays.stream(values()).filter(v -> v.packFormat >= packFormat).findFirst().orElse(null);
    }
}
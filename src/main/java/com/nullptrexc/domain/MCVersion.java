package com.nullptrexc.domain;

public class MCVersion implements Comparable<MCVersion> {
    private final int major;
    private final int minor;
    private final int patch;

    public MCVersion(String version) {
        String[] parts = version.split("\\.");
        if (parts.length > 3) {
            throw new IllegalArgumentException(version);
        }
        major = Integer.parseInt(parts[0]);
        minor = Integer.parseInt(parts[1]);
        patch = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
    }
    
    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    @Override
    public int compareTo(MCVersion other) {
        if (this.major != other.major) return Integer.compare(this.major, other.major);
        if (this.minor != other.minor) return Integer.compare(this.minor, other.minor);
        return Integer.compare(this.patch, other.patch);
    }
}

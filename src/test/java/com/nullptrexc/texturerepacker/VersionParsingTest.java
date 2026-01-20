package com.nullptrexc.texturerepacker;

import com.nullptrexc.texturerepacker.core.domain.MCVersion;
import com.nullptrexc.texturerepacker.core.domain.PackFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VersionParsingTest {
    @Test
    @DisplayName("Version comparison should handle equal versions correctly")
    void testVersionComparison() {
        MCVersion v1_8_9 = new MCVersion("1.8.9");
        MCVersion v1_16_1 = new MCVersion("1.16.1");
        MCVersion v1_10 = new MCVersion("1.10");
        MCVersion v1_8 = new MCVersion("1.8");

        assertEquals(0, v1_8_9.compareTo(new MCVersion("1.8.9")));
        assertEquals(0, v1_16_1.compareTo(new MCVersion("1.16.1")));
        assertTrue(v1_10.compareTo(v1_8_9) > 0, "1.10 should be greater than 1.8.9");
        assertTrue(v1_8_9.compareTo(v1_8) > 0, "1.8.9 should be greater than 1.8");
    }

    @Test
    @DisplayName("Should find correct pack format for boundary versions")
    void testPackFormatBoundaries() {
        // Test min boundaries
        assertEquals(PackFormat.V1, PackFormat.findPackFormat(new MCVersion("1.6.1")));
        assertEquals(PackFormat.V2, PackFormat.findPackFormat(new MCVersion("1.9")));
        assertEquals(PackFormat.V34, PackFormat.findPackFormat(new MCVersion("1.21")));

        // Test max boundaries
        assertEquals(PackFormat.V1, PackFormat.findPackFormat(new MCVersion("1.8.9")));
        assertEquals(PackFormat.V2, PackFormat.findPackFormat(new MCVersion("1.10.2")));
        assertEquals(PackFormat.V34, PackFormat.findPackFormat(new MCVersion("1.21.1")));
    }

    @Test
    @DisplayName("Should find correct pack format for middle versions in range")
    void testPackFormatMiddleVersions() {
        assertEquals(PackFormat.V1, PackFormat.findPackFormat(new MCVersion("1.7.10")));
        assertEquals(PackFormat.V2, PackFormat.findPackFormat(new MCVersion("1.10.1")));
        assertEquals(PackFormat.V5, PackFormat.findPackFormat(new MCVersion("1.16.0")));
        assertEquals(PackFormat.V22, PackFormat.findPackFormat(new MCVersion("1.20.4")));
    }

    @Test
    @DisplayName("Should handle single-version pack formats correctly")
    void testSingleVersionPackFormats() {
        // These pack formats only support one specific version
        assertEquals(PackFormat.V12, PackFormat.findPackFormat(new MCVersion("1.19.3")));
        assertEquals(PackFormat.V13, PackFormat.findPackFormat(new MCVersion("1.19.4")));
        assertEquals(PackFormat.V18, PackFormat.findPackFormat(new MCVersion("1.20.2")));
        assertEquals(PackFormat.V46, PackFormat.findPackFormat(new MCVersion("1.21.4")));
        assertEquals(PackFormat.V55, PackFormat.findPackFormat(new MCVersion("1.21.5")));
        assertEquals(PackFormat.V63, PackFormat.findPackFormat(new MCVersion("1.21.6")));
        assertEquals(PackFormat.V75, PackFormat.findPackFormat(new MCVersion("1.21.11")));

        // Just outside the single-version range should return different pack format or null
        assertNotEquals(PackFormat.V12, PackFormat.findPackFormat(new MCVersion("1.19.2")));
        assertNotEquals(PackFormat.V12, PackFormat.findPackFormat(new MCVersion("1.19.4")));
    }

    @Test
    @DisplayName("Should return null for versions outside any pack format range")
    void testInvalidVersions() {
        assertNull(PackFormat.findPackFormat(new MCVersion("1.5.2")), "1.5.2 is before any pack format");
        assertNull(PackFormat.findPackFormat(new MCVersion("1.1.1")), "1.1.1 is before any pack format");
        assertNull(PackFormat.findPackFormat(new MCVersion("2.0.0")), "2.0.0 doesn't exist yet");
        assertNull(PackFormat.findPackFormat(new MCVersion("20.20.20")), "Future version should return null");
    }

    @Test
    @DisplayName("Should find pack format by version string correctly")
    void testFindByVersionString() {
        assertEquals(PackFormat.V1, PackFormat.findPackFormat(1));
        assertEquals(PackFormat.V6, PackFormat.findPackFormat(6));
        assertEquals(PackFormat.V34, PackFormat.findPackFormat(34));
        assertEquals(PackFormat.V69, PackFormat.findPackFormat(69)); // nice
        assertEquals(PackFormat.V75, PackFormat.findPackFormat(75));

        assertNull(PackFormat.findPackFormat(0));
        assertNull(PackFormat.findPackFormat(-1));
        assertNull(PackFormat.findPackFormat(300));
        assertNull(PackFormat.findPackFormat(10)); // Skipped pack format number
        assertNull(PackFormat.findPackFormat(11)); // Skipped pack format number
    }

    @Test
    @DisplayName("Should verify all pack formats have valid ranges")
    void testPackFormatRangeValidity() {
        for (PackFormat format : PackFormat.values()) {
            assertTrue(format.getMin().compareTo(format.getMax()) <= 0,
                    format + " has min > max");
        }
    }

    @Test
    @DisplayName("Should handle versions between different pack formats correctly")
    void testVersionGapsBetweenPackFormats() {
        // 1.8.9 is last of V1, 1.9 is first of V2
        assertEquals(PackFormat.V1, PackFormat.findPackFormat(new MCVersion("1.8.9")));
        assertEquals(PackFormat.V2, PackFormat.findPackFormat(new MCVersion("1.9")));

        // 1.16.1 is last of V5, 1.16.2 is first of V6
        assertEquals(PackFormat.V5, PackFormat.findPackFormat(new MCVersion("1.16.1")));
        assertEquals(PackFormat.V6, PackFormat.findPackFormat(new MCVersion("1.16.2")));

        // 1.21.1 is last of V34, 1.21.2 is first of V42
        assertEquals(PackFormat.V34, PackFormat.findPackFormat(new MCVersion("1.21.1")));
        assertEquals(PackFormat.V42, PackFormat.findPackFormat(new MCVersion("1.21.2")));
    }

    @Test
    @DisplayName("Nice")
    void testNice() {
        assertEquals(69, PackFormat.V69.getFormat());
        assertTrue(PackFormat.V69.isCompatible(new MCVersion("1.21.9")));
        assertTrue(PackFormat.V69.isCompatible(new MCVersion("1.21.10")));
    }
}

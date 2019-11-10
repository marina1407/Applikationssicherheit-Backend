package ch.common.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Verschlüsselt das PAsswort.
 * @author Marina
 *
 */
public class Hasher {
    public static String hashString(String input) {
        return Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();
    }
}

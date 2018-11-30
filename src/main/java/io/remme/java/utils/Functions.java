package io.remme.java.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Functions {
    public static String generateAddress(String familyName, String data) {
        return DigestUtils.sha512Hex(familyName).substring(0, 6) + DigestUtils.sha512Hex(data).substring(0, 64);
    }

    public static String generateSettingsAddress(String key) {
        List<String> keyParts = Arrays.asList(key.split(".", 4));
        List<String> addressParts = keyParts.stream().map(v -> DigestUtils.sha256Hex(v).substring(0, 16))
                .collect(Collectors.toList());
        while (4 - addressParts.size() != 0) {
            addressParts.add(DigestUtils.sha256Hex("").substring(0, 16));
        }
        return "000000" + String.join("", addressParts);
    }
}

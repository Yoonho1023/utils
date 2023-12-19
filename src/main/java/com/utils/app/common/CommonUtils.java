package com.utils.app.common;

public class CommonUtils {

    /**
     * 여러개의 String을 붙혀서 key로 사용하는 경우 조합하여 key로 만드는 메서드 입니다.
     * ex) values -> apple, banana, kiwi
     * return -> applebananakiwi
     */
    public static String createKey(String... values) {
        StringBuilder keys = new StringBuilder();
        for (String v : values) {
            keys.append(v);
        }
        return keys.toString();
    }
}

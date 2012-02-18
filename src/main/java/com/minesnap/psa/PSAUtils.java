package com.minesnap.psa;

import java.lang.StringBuilder;

public class PSAUtils {
    private PSAUtils() {}

    public static String join(Object[] array, char separator) {
        if(array == null || array.length == 0)
            return "";

        StringBuilder sb = new StringBuilder(array[0].toString());

        for(int i=1; i<array.length; i++) {
            sb.append(separator);
            sb.append(array[i]);
        }

        return sb.toString();
    }
}

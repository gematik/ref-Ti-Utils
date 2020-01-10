/*
 * Copyright (c) 2020 gematik GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.gematik.ti.utils.codec;

/**
 * Converts hexadecimal Strings. The charset used for certain operation can be set, the default is set in DEFAULT_CHARSET_NAME This class is thread-safe.
 *
 * This class is inspired from org.apache.commons.codec.binary.Hex
 *
 * @author gerald.bartz
 * @version 1.0
 */
public class Hex {
    private static final int HEX_RADIX = 16;

    protected Hex() {

    }

    /**
     * Converts a String representing hexadecimal values into an array of bytes of those same values.
     *
     * @param data - byte array of data
     * @return byte array with hexadecimal values
     */
    public static byte[] decode(final String data) {
        if (data.length() % 2 != 0) {
            throw new IllegalArgumentException(String.format("Hex String %s not of even length", data));
        }

        byte[] b = new byte[data.length() / 2];

        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = toDigit(data.substring(index, index + 2));
            b[i] = (byte) v;
        }
        return b;
    }

    /**
     * Converts an array of bytes into a String representing the hexadecimal values of each byte in order.
     *
     * @param data - byte array of data
     * @return result as lowercase string
     */
    public static String encodeHexString(final byte[] data) {
        return encodeHexString(data, false);
    }

    /**
     * Converts an array of bytes into a String representing the hexadecimal values of each byte in order.
     *
     * @param data - byte array of data
     * @param toLowerCase - if true the result will return as lowercase string otherwise as uppercase string
     * @return string from byte array
     */
    public static String encodeHexString(final byte[] data, final boolean toLowerCase) {
        if (data == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        if (toLowerCase) {
            return sb.toString();
        } else {
            return sb.toString().toUpperCase();
        }

    }

    protected static int toDigit(final String hex) {
        return Integer.parseInt(hex, HEX_RADIX);
    }
}

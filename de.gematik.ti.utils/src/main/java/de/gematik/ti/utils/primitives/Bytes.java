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

package de.gematik.ti.utils.primitives;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Static utility methods pertaining to byte primitives, that are not already found in either Byte or Arrays,
 * and interpret bytes as neither signed nor unsigned.
 *
 * This class is inspired from com.google.common.primitives.Bytes (Apache License 2.0) http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * @author gerald.bartz
 * @version 1.0
 */
public class Bytes {
    private static final Logger LOG = LoggerFactory.getLogger(Bytes.class);

    private static final byte PAD = (byte) 0x80;

    protected Bytes() {

    }

    /**
     * Returns the values from each provided array combined into a single array. For example, {@code concat(new byte[] {a, b}, new byte[] {}, null, new byte[]
     * {c}} returns the array {@code {a, b, c}}.
     *
     * @param arrays
     *            zero or more nullable {@code byte} arrays
     * @return a single array containing all the values from the source arrays, in order
     */
    public static byte[] concatNullables(final byte[]... arrays) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] result = null;
        try {
            if (arrays != null) {
                for (final byte[] array : arrays) {
                    if (array != null) {
                        out.write(array, 0, array.length);
                    }
                }
            }
        } finally {
            result = out.toByteArray();
            try {
                out.close();
            } catch (IOException e) {
                LOG.error("Error {} on byteArrayOutputStream.close()", e);
            }
        }
        return result;
    }

    /**
     * Copying array elements
     *
     * @param input byte array
     * @param offset starting position in the input array
     * @param length the number of array elements to be copied
     * @return byte array with copied array elements
     */
    public static byte[] copyByteArray(final byte[] input, final int offset, final int length) {
        if (input == null) {
            return new byte[0];
        } else {
            if ((offset + length) > input.length) {
                throw new ArrayIndexOutOfBoundsException("copyByteArray data failed: Offset(" + offset + ") + length(" + length + ") > dataLength");
            }
            byte[] tmp = new byte[length];
            System.arraycopy(input, offset, tmp, 0, length);
            return tmp;
        }
    }

    /**
     * Padding the data
     *
     * @param data byte array with data
     * @param blockSize int
     * @return byte array with padded data
     */
    public static byte[] padData(final byte[] data, final int blockSize) {
        byte[] paddedData = new byte[data.length + (blockSize - data.length % blockSize)];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        paddedData[data.length] = PAD;
        return paddedData;
    }

    /**
     * Unpadding the data
     *
     * @param paddedData byte array with padded data
     * @return byte array with data
     */
    public static byte[] unPadData(final byte[] paddedData) {
        for (int i = paddedData.length - 1; i >= 0; i--) {
            if (paddedData[i] == PAD) {
                return copyByteArray(paddedData, 0, i);
            }
        }
        return paddedData;
    }

    /**
     * Converts a BigInteger into a ByteArray. A leading byte with the value 0 is truncated.
     *
     * @param bigInteger The BigInteger object to convert.
     * @return The ByteArray without leading 0-byte
     */
    public static byte[] bigIntToByteArray(final BigInteger bigInteger) {
        byte[] bigIntArray = bigInteger.toByteArray();
        byte[] returnArray;
        if (bigIntArray[0] == 0) {
            returnArray = new byte[bigIntArray.length - 1];
            System.arraycopy(bigIntArray, 1, returnArray, 0, returnArray.length);
            return returnArray;
        } else {
            return bigIntArray;
        }
    }
}

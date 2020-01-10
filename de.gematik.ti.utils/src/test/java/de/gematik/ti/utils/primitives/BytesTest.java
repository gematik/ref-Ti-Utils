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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import de.gematik.ti.utils.codec.Hex;

public class BytesTest {

    private static final String HEX1234ABCD = "1234ABCD";
    private static final String HEX111 = "111";
    private static final int RESULT_SIZE = 5;
    private static final int BLOCK_SIZE = 16;

    @Test
    public void shouldConcatenateWithEmptyByteArray() {
        byte[] nonempty = new byte[] { 0, -128, 44 };
        byte[] empty = new byte[0];
        byte[] concatEmpty1 = Bytes.concatNullables(empty, nonempty);
        byte[] concatEmpty2 = Bytes.concatNullables(nonempty, empty);
        Assert.assertThat(concatEmpty1, is(equalTo(nonempty)));
        Assert.assertThat(concatEmpty2, is(equalTo(nonempty)));
    }

    @Test
    public void shouldConcatenateWithNullByteArray() {
        byte[] nonempty = new byte[] { 0, -128, 44 };
        byte[] concatNull1 = Bytes.concatNullables(null, nonempty);
        byte[] concatNull2 = Bytes.concatNullables(nonempty, null);
        Assert.assertThat(concatNull1, is(equalTo(nonempty)));
        Assert.assertThat(concatNull2, is(equalTo(nonempty)));
    }

    @Test
    public void shouldConcatenateCombineTwoArray() {
        byte[] bytesOne = new byte[] { 0, -128, 44 };
        byte[] bytesTwo = new byte[] { 1, 2, 3 };
        byte[] concatEmpty1 = Bytes.concatNullables(bytesTwo, bytesOne);
        Assert.assertThat(concatEmpty1, is(equalTo(new byte[] { 1, 2, 3, 0, -128, 44 })));
        byte[] concatEmpty2 = Bytes.concatNullables(bytesOne, bytesTwo);
        Assert.assertThat(concatEmpty2, is(equalTo(new byte[] { 0, -128, 44, 1, 2, 3 })));
    }

    @Test
    public void shouldConcatenateCombineFoueArray() {
        byte[] bytesOne = new byte[] { 0, -128, 44 };
        byte[] bytesTwo = new byte[] { 1, 2, 3 };
        byte[] concatEmpty1 = Bytes.concatNullables(bytesTwo, bytesOne, bytesOne, bytesTwo);
        Assert.assertThat(concatEmpty1, is(equalTo(new byte[] { 1, 2, 3, 0, -128, 44, 0, -128, 44, 1, 2, 3 })));
        byte[] concatEmpty2 = Bytes.concatNullables(bytesOne, bytesTwo, bytesOne, bytesTwo);
        Assert.assertThat(concatEmpty2, is(equalTo(new byte[] { 0, -128, 44, 1, 2, 3, 0, -128, 44, 1, 2, 3 })));
    }

    @Test
    public void shouldConcatenateNull() {
        byte[] bytesOne = null;
        byte[] bytesTwo = null;
        byte[] concatEmpty1 = Bytes.concatNullables(bytesTwo, bytesOne, bytesOne, bytesTwo);
        Assert.assertThat(concatEmpty1, is(equalTo(new byte[] {})));
        byte[] concatEmpty2 = Bytes.concatNullables(bytesOne, bytesTwo, bytesOne, bytesTwo);
        Assert.assertThat(concatEmpty2, is(equalTo(new byte[] {})));
    }

    @Test
    public void testCopyByteArray() {
        byte[] input = Hex.decode("000001020304050607");
        byte[] expected = Hex.decode("0102030405");
        byte[] result = Bytes.copyByteArray(input, 2, RESULT_SIZE);
        Assert.assertEquals(Hex.encodeHexString(expected), Hex.encodeHexString(result));
        Assert.assertNotNull(Bytes.copyByteArray(null, 0, 0));

        try {
            Bytes.copyByteArray(input, RESULT_SIZE, RESULT_SIZE);
            Assert.fail("copying with offset + length > data length should fail");
        } catch (Exception e) {
            // expected
        }
    }

    @Test
    public void shouldAddAndRemovePadding() {
        byte[] unhandedData = Hex.decode("05060708090A");
        byte[] paddedData = Bytes.padData(unhandedData, BLOCK_SIZE);
        byte[] expectedPaddedData = Hex.decode("05060708090A80000000000000000000");
        Assert.assertEquals(paddedData.length, BLOCK_SIZE);
        Assert.assertEquals(Hex.encodeHexString(expectedPaddedData), Hex.encodeHexString(paddedData));

        byte[] unPaddedData = Bytes.unPadData(paddedData);
        Assert.assertEquals(Hex.encodeHexString(unhandedData), Hex.encodeHexString(unPaddedData));
    }

    @Test
    public void shouldCreateByteArrayFromBigIntWithoutLeadingNullByte() {
        BigInteger bigInt = new BigInteger("0127");
        BigInteger bigInt1 = new BigInteger("127");

        byte[] bigIntArray1 = Bytes.bigIntToByteArray(bigInt);
        byte[] bigIntArray2 = Bytes.bigIntToByteArray(bigInt1);

        byte[] expectedByteArray = Hex.decode("7F");

        Assert.assertEquals(Hex.encodeHexString(expectedByteArray), Hex.encodeHexString(bigIntArray1));
        Assert.assertEquals(Hex.encodeHexString(expectedByteArray), Hex.encodeHexString(bigIntArray2));
    }

}

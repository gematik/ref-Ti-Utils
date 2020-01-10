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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HexTest {

    private static final String HEX1234ABCD = "1234ABCD";
    private static final String HEX111 = "111";

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    public void shouldBeEqualConvertedHexString() {
        String actual = Hex.encodeHexString(Hex.decode(HEX1234ABCD), false);
        Assert.assertThat(actual, is(equalTo(HEX1234ABCD)));
    }

    @Test
    public void shouldFailHexConversionCauseOddLengthHexString() {
        exceptions.expect(IllegalArgumentException.class);
        Hex.decode(HEX111);
    }

}

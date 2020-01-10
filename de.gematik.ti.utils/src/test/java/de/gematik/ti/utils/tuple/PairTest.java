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

package de.gematik.ti.utils.tuple;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PairTest {

    static Pair pair1 = new Pair("str", 1);
    static Pair pair1_1 = new Pair("str", 1);
    static Pair pair2 = new Pair(1, 1);
    static Pair pair3 = new Pair("str", 2);

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    public void shouldBeEqual() {
        Assert.assertEquals(pair1, pair1);
        Assert.assertEquals(pair1, pair1_1);
        Assert.assertEquals(pair1.hashCode(), pair1.hashCode());
        Assert.assertEquals(pair1.hashCode(), pair1_1.hashCode());
    }

    @Test
    public void shouldNotBeEqual() {
        Assert.assertNotEquals(pair1, pair2);
        Assert.assertNotEquals(pair1, pair3);
        Assert.assertNotEquals(pair2, pair3);
        Assert.assertNotEquals(pair2.hashCode(), pair3.hashCode());
        Assert.assertNotEquals(pair2.hashCode(), pair3.hashCode());
        Assert.assertNotEquals(pair2.hashCode(), pair3.hashCode());
    }
}


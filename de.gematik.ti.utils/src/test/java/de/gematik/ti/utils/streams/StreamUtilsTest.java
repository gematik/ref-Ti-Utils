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

package de.gematik.ti.utils.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import de.gematik.ti.utils.streams.exceptions.StreamRuntimeException;

public class StreamUtilsTest {

    // tag::definelist[]
    private List<ExceptionClass> list;
    // end::definelist[]

    @Before
    public void setup() {
        // tag::setup[]
        list = Arrays.asList(new ExceptionClass(), new ExceptionClass(), new ExceptionClass());
        // end::setup[]
    }

    @Test(expected = StreamRuntimeException.class)
    public void wrapConsumer() throws Exception {
        // tag::wrapConsumer[]
        list.stream().forEach(new StreamUtils().wrapConsumer(v -> v.testMethod()));
        // end::wrapConsumer[]
    }

    @Test(expected = StreamRuntimeException.class)
    public void wrapFunction() throws Exception {
        // tag::wrapFunction[]
        list.stream().map(new StreamUtils().wrapFunction(v -> v.getValue())).collect(Collectors.toList());
        // end::wrapFunction[]
    }

    // tag::ExceptionClass[]
    class ExceptionClass {
        public void testMethod() throws Exception {
            throw new Exception("test");
        }

        public String getValue() throws Exception {
            throw new Exception("test");
        }
    }
    // end::ExceptionClass[]

}

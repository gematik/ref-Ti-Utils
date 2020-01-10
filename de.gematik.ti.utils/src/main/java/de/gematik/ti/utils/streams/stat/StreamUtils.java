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

package de.gematik.ti.utils.streams.stat;

import java.util.function.Consumer;
import java.util.function.Function;

import de.gematik.ti.utils.streams.inf.IConsumerWithException;
import de.gematik.ti.utils.streams.inf.IFunctionWithException;

/**
 * include::{userguide}/TIUTILS_Structure.adoc[tag=StreamUtils]
 * Wrap the methods and create new instance of StreamUtils for each static call
 *
 * @author christian.lange
 * @version 1.0
 */
public final class StreamUtils {

    private StreamUtils() {
    }

    /**
     * Execute the apply functionality on given object and capture the maybe thrown exception
     *
     * Use example @code LIST.stream().map(wrap(object -> object.doSome())).NEXT
     *
     * @param fe - object to call apply
     * @param <T> - object type
     * @param <R> - return type
     * @param <E> - exception type
     * @return value from apply method on given object
     */
    public static synchronized <T, R, E extends Exception> Function<T, R> wrapFunction(IFunctionWithException<T, R, E> fe) {
        return new de.gematik.ti.utils.streams.StreamUtils().wrapFunction(fe);
    }

    /**
     * Execute the accept functionality on given object and capture the maybe thrown exception
     * Use example @code LIST.stream().foreach(wrap(object -> object.doSome())).NEXT
     *
     * @param consumer - object to call accept
     * @param <T> - object type
     * @param <E> - exception type
     */
    public static synchronized <T, E extends Exception> Consumer<T> wrapConsumer(IConsumerWithException<T, E> consumer) {
        return new de.gematik.ti.utils.streams.StreamUtils().wrapConsumer(consumer);
    }

}

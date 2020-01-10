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

package de.gematik.ti.utils.streams.inf;

/**
 * Functional Interface
 *
 * @param <T> type of apply method
 * @param <R> type of return value
 * @param <E> specific type of thrown exception
 *
 * @author christian.lange
 * @version 1.0
 */
@FunctionalInterface
public interface IFunctionWithException<T, R, E extends Exception> {
    /**
     * Method to do something with given parameter and could thrown a specific exception
     * @param t type of apply
     * @return R type of return value
     * @throws E specific type of thrown exception
     */
    R apply(T t) throws E;
}

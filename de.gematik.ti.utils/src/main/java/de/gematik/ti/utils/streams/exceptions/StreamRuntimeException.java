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

package de.gematik.ti.utils.streams.exceptions;

/**
 * Runtime Exception Class for Exception thrown by stream handling
 *
 * @author christian.lange
 * @version 1.0
 */
public class StreamRuntimeException extends RuntimeException {

    public StreamRuntimeException() {
    }

    /**
     * With Message for receiver
     * @param message - Message
     */
    public StreamRuntimeException(final String message) {
        super(message);
    }

    /**
     * With Message and cause for receiver
     * @param message - Message
     * @param cause - cause for this exception
     */
    public StreamRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * With for receiver
     * @param cause - cause for this exception
     */
    public StreamRuntimeException(final Throwable cause) {
        super(cause);
    }

    /**
     * With Message and cause such as other details for receiver
     * @param message - Message
     * @param cause - cause for this exception
     * @param enableSuppression - enabling of suppression
     * @param writableStackTrace - parameter for receiver if stacktrace write
     */
    public StreamRuntimeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

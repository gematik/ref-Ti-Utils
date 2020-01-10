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

/**
 * Overly simple implementation of a generic Pair consisting of two elements.
 *
 * Elements left and right are directly accessible.
 * @param <L>
 * @param <R>
 */
public class Pair<L, R> {
    public final L left;
    public final R right;

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Pair<?, ?>) {
            final Pair<?, ?> other = (Pair<?, ?>) obj;
            return other.left.equals(this.left) && other.right.equals(this.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.left == null ? 0
                : this.left.hashCode() ^
                        (this.right == null ? 0 : this.right.hashCode()));
    }

    @Override
    public String toString() {
        return "(" + this.left + ',' + this.right + ')';
    }
}

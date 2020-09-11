/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.type.indexeddb;

/**
 * Key range
 */
public class KeyRange {
    private Key lower;

    private Key upper;

    private Boolean lowerOpen;

    private Boolean upperOpen;

    /**
     * Lower bound.
     */
    public Key getLower() {
        return lower;
    }

    /**
     * Lower bound.
     */
    public void setLower(Key lower) {
        this.lower = lower;
    }

    /**
     * Upper bound.
     */
    public Key getUpper() {
        return upper;
    }

    /**
     * Upper bound.
     */
    public void setUpper(Key upper) {
        this.upper = upper;
    }

    /**
     * If true lower bound is open.
     */
    public Boolean isLowerOpen() {
        return lowerOpen;
    }

    /**
     * If true lower bound is open.
     */
    public void setLowerOpen(Boolean lowerOpen) {
        this.lowerOpen = lowerOpen;
    }

    /**
     * If true upper bound is open.
     */
    public Boolean isUpperOpen() {
        return upperOpen;
    }

    /**
     * If true upper bound is open.
     */
    public void setUpperOpen(Boolean upperOpen) {
        this.upperOpen = upperOpen;
    }
}

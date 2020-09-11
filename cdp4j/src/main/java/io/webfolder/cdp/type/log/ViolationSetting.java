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
package io.webfolder.cdp.type.log;

import io.webfolder.cdp.type.constant.ViolationType;

/**
 * Violation configuration setting
 */
public class ViolationSetting {
    private ViolationType name;

    private Double threshold;

    /**
     * Violation type.
     */
    public ViolationType getName() {
        return name;
    }

    /**
     * Violation type.
     */
    public void setName(ViolationType name) {
        this.name = name;
    }

    /**
     * Time threshold to trigger upon.
     */
    public Double getThreshold() {
        return threshold;
    }

    /**
     * Time threshold to trigger upon.
     */
    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }
}

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
package io.webfolder.cdp.type.profiler;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Specifies a number of samples attributed to a certain source position
 */
@Experimental
public class PositionTickInfo {
    private Integer line;

    private Integer ticks;

    /**
     * Source line number (1-based).
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Source line number (1-based).
     */
    public void setLine(Integer line) {
        this.line = line;
    }

    /**
     * Number of samples attributed to the source line.
     */
    public Integer getTicks() {
        return ticks;
    }

    /**
     * Number of samples attributed to the source line.
     */
    public void setTicks(Integer ticks) {
        this.ticks = ticks;
    }
}

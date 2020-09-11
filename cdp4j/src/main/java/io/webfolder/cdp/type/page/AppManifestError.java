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
package io.webfolder.cdp.type.page;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Error while paring app manifest
 */
@Experimental
public class AppManifestError {
    private String message;

    private Integer critical;

    private Integer line;

    private Integer column;

    /**
     * Error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * If criticial, this is a non-recoverable parse error.
     */
    public Integer getCritical() {
        return critical;
    }

    /**
     * If criticial, this is a non-recoverable parse error.
     */
    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    /**
     * Error line.
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Error line.
     */
    public void setLine(Integer line) {
        this.line = line;
    }

    /**
     * Error column.
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Error column.
     */
    public void setColumn(Integer column) {
        this.column = column;
    }
}

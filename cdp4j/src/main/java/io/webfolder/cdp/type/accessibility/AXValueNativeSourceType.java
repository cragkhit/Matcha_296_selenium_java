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
package io.webfolder.cdp.type.accessibility;

import com.google.gson.annotations.SerializedName;

/**
 * Enum of possible native property sources (as a subtype of a particular AXValueSourceType)
 */
public enum AXValueNativeSourceType {
    @SerializedName("figcaption")
    Figcaption("figcaption"),

    @SerializedName("label")
    Label("label"),

    @SerializedName("labelfor")
    Labelfor("labelfor"),

    @SerializedName("labelwrapped")
    Labelwrapped("labelwrapped"),

    @SerializedName("legend")
    Legend("legend"),

    @SerializedName("tablecaption")
    Tablecaption("tablecaption"),

    @SerializedName("title")
    Title("title"),

    @SerializedName("other")
    Other("other");

    public final String value;

    AXValueNativeSourceType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

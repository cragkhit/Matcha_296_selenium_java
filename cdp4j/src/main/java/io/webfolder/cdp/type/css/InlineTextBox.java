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
package io.webfolder.cdp.type.css;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.dom.Rect;

/**
 * Details of post layout rendered text positions
 * The exact layout should not be regarded as stable and may change between versions
 */
@Experimental
public class InlineTextBox {
    private Rect boundingBox;

    private Integer startCharacterIndex;

    private Integer numCharacters;

    /**
     * The absolute position bounding box.
     */
    public Rect getBoundingBox() {
        return boundingBox;
    }

    /**
     * The absolute position bounding box.
     */
    public void setBoundingBox(Rect boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * The starting index in characters, for this post layout textbox substring.
     */
    public Integer getStartCharacterIndex() {
        return startCharacterIndex;
    }

    /**
     * The starting index in characters, for this post layout textbox substring.
     */
    public void setStartCharacterIndex(Integer startCharacterIndex) {
        this.startCharacterIndex = startCharacterIndex;
    }

    /**
     * The number of characters in this post layout textbox substring.
     */
    public Integer getNumCharacters() {
        return numCharacters;
    }

    /**
     * The number of characters in this post layout textbox substring.
     */
    public void setNumCharacters(Integer numCharacters) {
        this.numCharacters = numCharacters;
    }
}

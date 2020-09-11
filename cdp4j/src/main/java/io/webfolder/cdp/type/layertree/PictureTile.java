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
package io.webfolder.cdp.type.layertree;

/**
 * Serialized fragment of layer picture along with its offset within the layer
 */
public class PictureTile {
    private Double x;

    private Double y;

    private String picture;

    /**
     * Offset from owning layer left boundary
     */
    public Double getX() {
        return x;
    }

    /**
     * Offset from owning layer left boundary
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Offset from owning layer top boundary
     */
    public Double getY() {
        return y;
    }

    /**
     * Offset from owning layer top boundary
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * Base64-encoded snapshot data.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Base64-encoded snapshot data.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}

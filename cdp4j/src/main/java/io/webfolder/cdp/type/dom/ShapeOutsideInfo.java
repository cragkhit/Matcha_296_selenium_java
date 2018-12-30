/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2018 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.type.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * CSS Shape Outside details
 */
public class ShapeOutsideInfo {
    private List<Double> bounds = new ArrayList<>();

    private List<Object> shape = new ArrayList<>();

    private List<Object> marginShape = new ArrayList<>();

    /**
     * Shape bounds
     */
    public List<Double> getBounds() {
        return bounds;
    }

    /**
     * Shape bounds
     */
    public void setBounds(List<Double> bounds) {
        this.bounds = bounds;
    }

    /**
     * Shape coordinate details
     */
    public List<Object> getShape() {
        return shape;
    }

    /**
     * Shape coordinate details
     */
    public void setShape(List<Object> shape) {
        this.shape = shape;
    }

    /**
     * Margin shape bounds
     */
    public List<Object> getMarginShape() {
        return marginShape;
    }

    /**
     * Margin shape bounds
     */
    public void setMarginShape(List<Object> marginShape) {
        this.marginShape = marginShape;
    }
}

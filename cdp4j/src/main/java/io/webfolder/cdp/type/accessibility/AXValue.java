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

import java.util.ArrayList;
import java.util.List;

/**
 * A single computed AX property
 */
public class AXValue {
    private AXValueType type;

    private Object value;

    private List<AXRelatedNode> relatedNodes = new ArrayList<>();

    private List<AXValueSource> sources = new ArrayList<>();

    /**
     * The type of this value.
     */
    public AXValueType getType() {
        return type;
    }

    /**
     * The type of this value.
     */
    public void setType(AXValueType type) {
        this.type = type;
    }

    /**
     * The computed value of this property.
     */
    public Object getValue() {
        return value;
    }

    /**
     * The computed value of this property.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * One or more related nodes, if applicable.
     */
    public List<AXRelatedNode> getRelatedNodes() {
        return relatedNodes;
    }

    /**
     * One or more related nodes, if applicable.
     */
    public void setRelatedNodes(List<AXRelatedNode> relatedNodes) {
        this.relatedNodes = relatedNodes;
    }

    /**
     * The sources which contributed to the computation of this property.
     */
    public List<AXValueSource> getSources() {
        return sources;
    }

    /**
     * The sources which contributed to the computation of this property.
     */
    public void setSources(List<AXValueSource> sources) {
        this.sources = sources;
    }
}

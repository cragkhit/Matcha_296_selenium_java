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
package io.webfolder.cdp.event.dom;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.dom.Node;

/**
 * Called when a pseudo element is added to an element
 */
@Experimental
@Domain("DOM")
@EventName("pseudoElementAdded")
public class PseudoElementAdded {
    private Integer parentId;

    private Node pseudoElement;

    /**
     * Pseudo element's parent element id.
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * Pseudo element's parent element id.
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * The added pseudo element.
     */
    public Node getPseudoElement() {
        return pseudoElement;
    }

    /**
     * The added pseudo element.
     */
    public void setPseudoElement(Node pseudoElement) {
        this.pseudoElement = pseudoElement;
    }
}

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

public class AXRelatedNode {
    private Integer backendDOMNodeId;

    private String idref;

    private String text;

    /**
     * The BackendNodeId of the related DOM node.
     */
    public Integer getBackendDOMNodeId() {
        return backendDOMNodeId;
    }

    /**
     * The BackendNodeId of the related DOM node.
     */
    public void setBackendDOMNodeId(Integer backendDOMNodeId) {
        this.backendDOMNodeId = backendDOMNodeId;
    }

    /**
     * The IDRef value provided, if any.
     */
    public String getIdref() {
        return idref;
    }

    /**
     * The IDRef value provided, if any.
     */
    public void setIdref(String idref) {
        this.idref = idref;
    }

    /**
     * The text alternative of this node in the current context.
     */
    public String getText() {
        return text;
    }

    /**
     * The text alternative of this node in the current context.
     */
    public void setText(String text) {
        this.text = text;
    }
}

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
 * A node in the accessibility tree
 */
public class AXNode {
    private String nodeId;

    private Boolean ignored;

    private List<AXProperty> ignoredReasons = new ArrayList<>();

    private AXValue role;

    private AXValue name;

    private AXValue description;

    private AXValue value;

    private List<AXProperty> properties = new ArrayList<>();

    private Integer backendDOMNodeId;

    /**
     * Unique identifier for this node.
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Unique identifier for this node.
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * Whether this node is ignored for accessibility
     */
    public Boolean isIgnored() {
        return ignored;
    }

    /**
     * Whether this node is ignored for accessibility
     */
    public void setIgnored(Boolean ignored) {
        this.ignored = ignored;
    }

    /**
     * Collection of reasons why this node is hidden.
     */
    public List<AXProperty> getIgnoredReasons() {
        return ignoredReasons;
    }

    /**
     * Collection of reasons why this node is hidden.
     */
    public void setIgnoredReasons(List<AXProperty> ignoredReasons) {
        this.ignoredReasons = ignoredReasons;
    }

    /**
     * This <code>Node</code>'s role, whether explicit or implicit.
     */
    public AXValue getRole() {
        return role;
    }

    /**
     * This <code>Node</code>'s role, whether explicit or implicit.
     */
    public void setRole(AXValue role) {
        this.role = role;
    }

    /**
     * The accessible name for this <code>Node</code>.
     */
    public AXValue getName() {
        return name;
    }

    /**
     * The accessible name for this <code>Node</code>.
     */
    public void setName(AXValue name) {
        this.name = name;
    }

    /**
     * The accessible description for this <code>Node</code>.
     */
    public AXValue getDescription() {
        return description;
    }

    /**
     * The accessible description for this <code>Node</code>.
     */
    public void setDescription(AXValue description) {
        this.description = description;
    }

    /**
     * The value for this <code>Node</code>.
     */
    public AXValue getValue() {
        return value;
    }

    /**
     * The value for this <code>Node</code>.
     */
    public void setValue(AXValue value) {
        this.value = value;
    }

    /**
     * All other properties
     */
    public List<AXProperty> getProperties() {
        return properties;
    }

    /**
     * All other properties
     */
    public void setProperties(List<AXProperty> properties) {
        this.properties = properties;
    }

    /**
     * The backend ID for the associated DOM node, if any.
     */
    public Integer getBackendDOMNodeId() {
        return backendDOMNodeId;
    }

    /**
     * The backend ID for the associated DOM node, if any.
     */
    public void setBackendDOMNodeId(Integer backendDOMNodeId) {
        this.backendDOMNodeId = backendDOMNodeId;
    }
}

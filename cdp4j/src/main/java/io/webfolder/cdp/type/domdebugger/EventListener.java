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
package io.webfolder.cdp.type.domdebugger;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.runtime.RemoteObject;

/**
 * Object event listener
 */
@Experimental
public class EventListener {
    private String type;

    private Boolean useCapture;

    private Boolean passive;

    private Boolean once;

    private String scriptId;

    private Integer lineNumber;

    private Integer columnNumber;

    private RemoteObject handler;

    private RemoteObject originalHandler;

    private Integer backendNodeId;

    /**
     * <code>EventListener</code>'s type.
     */
    public String getType() {
        return type;
    }

    /**
     * <code>EventListener</code>'s type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * <code>EventListener</code>'s useCapture.
     */
    public Boolean isUseCapture() {
        return useCapture;
    }

    /**
     * <code>EventListener</code>'s useCapture.
     */
    public void setUseCapture(Boolean useCapture) {
        this.useCapture = useCapture;
    }

    /**
     * <code>EventListener</code>'s passive flag.
     */
    public Boolean isPassive() {
        return passive;
    }

    /**
     * <code>EventListener</code>'s passive flag.
     */
    public void setPassive(Boolean passive) {
        this.passive = passive;
    }

    /**
     * <code>EventListener</code>'s once flag.
     */
    public Boolean isOnce() {
        return once;
    }

    /**
     * <code>EventListener</code>'s once flag.
     */
    public void setOnce(Boolean once) {
        this.once = once;
    }

    /**
     * Script id of the handler code.
     */
    public String getScriptId() {
        return scriptId;
    }

    /**
     * Script id of the handler code.
     */
    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    /**
     * Line number in the script (0-based).
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Line number in the script (0-based).
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Column number in the script (0-based).
     */
    public Integer getColumnNumber() {
        return columnNumber;
    }

    /**
     * Column number in the script (0-based).
     */
    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * Event handler function value.
     */
    public RemoteObject getHandler() {
        return handler;
    }

    /**
     * Event handler function value.
     */
    public void setHandler(RemoteObject handler) {
        this.handler = handler;
    }

    /**
     * Event original handler function value.
     */
    public RemoteObject getOriginalHandler() {
        return originalHandler;
    }

    /**
     * Event original handler function value.
     */
    public void setOriginalHandler(RemoteObject originalHandler) {
        this.originalHandler = originalHandler;
    }

    /**
     * Node the listener is added to (if any).
     */
    public Integer getBackendNodeId() {
        return backendNodeId;
    }

    /**
     * Node the listener is added to (if any).
     */
    public void setBackendNodeId(Integer backendNodeId) {
        this.backendNodeId = backendNodeId;
    }
}

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
 * Information about the Resource on the page
 */
@Experimental
public class FrameResource {
    private String url;

    private ResourceType type;

    private String mimeType;

    private Double lastModified;

    private Double contentSize;

    private Boolean failed;

    private Boolean canceled;

    /**
     * Resource URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Resource URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Type of this resource.
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Type of this resource.
     */
    public void setType(ResourceType type) {
        this.type = type;
    }

    /**
     * Resource mimeType as determined by the browser.
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Resource mimeType as determined by the browser.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * last-modified timestamp as reported by server.
     */
    public Double getLastModified() {
        return lastModified;
    }

    /**
     * last-modified timestamp as reported by server.
     */
    public void setLastModified(Double lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Resource content size.
     */
    public Double getContentSize() {
        return contentSize;
    }

    /**
     * Resource content size.
     */
    public void setContentSize(Double contentSize) {
        this.contentSize = contentSize;
    }

    /**
     * True if the resource failed to load.
     */
    public Boolean isFailed() {
        return failed;
    }

    /**
     * True if the resource failed to load.
     */
    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    /**
     * True if the resource was canceled during loading.
     */
    public Boolean isCanceled() {
        return canceled;
    }

    /**
     * True if the resource was canceled during loading.
     */
    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }
}

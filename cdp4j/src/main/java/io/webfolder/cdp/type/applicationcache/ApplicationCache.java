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
package io.webfolder.cdp.type.applicationcache;

import java.util.ArrayList;
import java.util.List;

/**
 * Detailed application cache information
 */
public class ApplicationCache {
    private String manifestURL;

    private Double size;

    private Double creationTime;

    private Double updateTime;

    private List<ApplicationCacheResource> resources = new ArrayList<>();

    /**
     * Manifest URL.
     */
    public String getManifestURL() {
        return manifestURL;
    }

    /**
     * Manifest URL.
     */
    public void setManifestURL(String manifestURL) {
        this.manifestURL = manifestURL;
    }

    /**
     * Application cache size.
     */
    public Double getSize() {
        return size;
    }

    /**
     * Application cache size.
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * Application cache creation time.
     */
    public Double getCreationTime() {
        return creationTime;
    }

    /**
     * Application cache creation time.
     */
    public void setCreationTime(Double creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Application cache update time.
     */
    public Double getUpdateTime() {
        return updateTime;
    }

    /**
     * Application cache update time.
     */
    public void setUpdateTime(Double updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Application cache resources.
     */
    public List<ApplicationCacheResource> getResources() {
        return resources;
    }

    /**
     * Application cache resources.
     */
    public void setResources(List<ApplicationCacheResource> resources) {
        this.resources = resources;
    }
}

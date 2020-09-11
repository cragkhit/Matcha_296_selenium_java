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

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Information about the Frame hierarchy along with their cached resources
 */
@Experimental
public class FrameResourceTree {
    private Frame frame;

    private List<FrameResourceTree> childFrames = new ArrayList<>();

    private List<FrameResource> resources = new ArrayList<>();

    /**
     * Frame information for this tree item.
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Frame information for this tree item.
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * Child frames.
     */
    public List<FrameResourceTree> getChildFrames() {
        return childFrames;
    }

    /**
     * Child frames.
     */
    public void setChildFrames(List<FrameResourceTree> childFrames) {
        this.childFrames = childFrames;
    }

    /**
     * Information about frame resources.
     */
    public List<FrameResource> getResources() {
        return resources;
    }

    /**
     * Information about frame resources.
     */
    public void setResources(List<FrameResource> resources) {
        this.resources = resources;
    }
}

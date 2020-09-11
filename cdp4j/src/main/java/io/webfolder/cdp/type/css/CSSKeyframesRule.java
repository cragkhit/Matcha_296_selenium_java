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
package io.webfolder.cdp.type.css;

import java.util.ArrayList;
import java.util.List;

/**
 * CSS keyframes rule representation
 */
public class CSSKeyframesRule {
    private Value animationName;

    private List<CSSKeyframeRule> keyframes = new ArrayList<>();

    /**
     * Animation name.
     */
    public Value getAnimationName() {
        return animationName;
    }

    /**
     * Animation name.
     */
    public void setAnimationName(Value animationName) {
        this.animationName = animationName;
    }

    /**
     * List of keyframes.
     */
    public List<CSSKeyframeRule> getKeyframes() {
        return keyframes;
    }

    /**
     * List of keyframes.
     */
    public void setKeyframes(List<CSSKeyframeRule> keyframes) {
        this.keyframes = keyframes;
    }
}

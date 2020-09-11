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

import io.webfolder.cdp.type.dom.PseudoType;

/**
 * CSS rule collection for a single pseudo style
 */
public class PseudoElementMatches {
    private PseudoType pseudoType;

    private List<RuleMatch> matches = new ArrayList<>();

    /**
     * Pseudo element type.
     */
    public PseudoType getPseudoType() {
        return pseudoType;
    }

    /**
     * Pseudo element type.
     */
    public void setPseudoType(PseudoType pseudoType) {
        this.pseudoType = pseudoType;
    }

    /**
     * Matches of CSS rules applicable to the pseudo style.
     */
    public List<RuleMatch> getMatches() {
        return matches;
    }

    /**
     * Matches of CSS rules applicable to the pseudo style.
     */
    public void setMatches(List<RuleMatch> matches) {
        this.matches = matches;
    }
}

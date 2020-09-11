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
 * CSS style representation
 */
public class CSSStyle {
    private String styleSheetId;

    private List<CSSProperty> cssProperties = new ArrayList<>();

    private List<ShorthandEntry> shorthandEntries = new ArrayList<>();

    private String cssText;

    private SourceRange range;

    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
     */
    public String getStyleSheetId() {
        return styleSheetId;
    }

    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
     */
    public void setStyleSheetId(String styleSheetId) {
        this.styleSheetId = styleSheetId;
    }

    /**
     * CSS properties in the style.
     */
    public List<CSSProperty> getCssProperties() {
        return cssProperties;
    }

    /**
     * CSS properties in the style.
     */
    public void setCssProperties(List<CSSProperty> cssProperties) {
        this.cssProperties = cssProperties;
    }

    /**
     * Computed values for all shorthands found in the style.
     */
    public List<ShorthandEntry> getShorthandEntries() {
        return shorthandEntries;
    }

    /**
     * Computed values for all shorthands found in the style.
     */
    public void setShorthandEntries(List<ShorthandEntry> shorthandEntries) {
        this.shorthandEntries = shorthandEntries;
    }

    /**
     * Style declaration text (if available).
     */
    public String getCssText() {
        return cssText;
    }

    /**
     * Style declaration text (if available).
     */
    public void setCssText(String cssText) {
        this.cssText = cssText;
    }

    /**
     * Style declaration range in the enclosing stylesheet (if available).
     */
    public SourceRange getRange() {
        return range;
    }

    /**
     * Style declaration range in the enclosing stylesheet (if available).
     */
    public void setRange(SourceRange range) {
        this.range = range;
    }
}

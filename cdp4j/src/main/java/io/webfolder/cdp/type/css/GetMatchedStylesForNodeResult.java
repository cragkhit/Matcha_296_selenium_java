/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2018 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.type.css;

import java.util.List;

public class GetMatchedStylesForNodeResult {
    private CSSStyle inlineStyle;

    private CSSStyle attributesStyle;

    private List<RuleMatch> matchedCSSRules;

    private List<PseudoElementMatches> pseudoElements;

    private List<InheritedStyleEntry> inherited;

    private List<CSSKeyframesRule> cssKeyframesRules;

    public CSSStyle getInlineStyle() {
        return inlineStyle;
    }

    public CSSStyle getAttributesStyle() {
        return attributesStyle;
    }

    public List<RuleMatch> getMatchedCSSRules() {
        return matchedCSSRules;
    }

    public List<PseudoElementMatches> getPseudoElements() {
        return pseudoElements;
    }

    public List<InheritedStyleEntry> getInherited() {
        return inherited;
    }

    public List<CSSKeyframesRule> getCssKeyframesRules() {
        return cssKeyframesRules;
    }
}

/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.util.html.markup;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.HeadTag;
import org.htmlparser.tags.Html;
import org.htmlparser.tags.TitleTag;

import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.html.L2BodyTag;
import com.l2jserver.util.html.L2HeadTag;
import com.l2jserver.util.html.L2HtmlTag;
import com.l2jserver.util.html.L2TitleTag;

/**
 * This an helper class used to build HTML documents. You should implement
 * {@link HtmlTemplate} as an anonymous class:
 * 
 * <pre>
 * final HtmlTemplate template = new HtmlTemplate(&quot;Template implementation sample&quot;) {
 * 	&#064;Override
 * 	public void build(MarkupTag body) {
 * 		body.text(&quot;Hello world!&quot;).br().text(&quot;New line!&quot;);
 * 	}
 * };
 * final String html = template.toHtmlString();
 * </pre>
 * 
 * Using the {@link Html} object (from {@link #build()}) you can obtain the HTML
 * code using {@link Html#toHtml()}.
 * <p>
 * The generated code will be the smaller possible because the client has packet
 * size limitations, it will not contain any formatting.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class HtmlTemplate {
	/**
	 * The template title (will be displayed in the game window)
	 */
	private final String title;
	/**
	 * The template variables
	 */
	private Map<String, String> variables = CollectionFactory.newMap();

	/**
	 * Creates a new instance
	 * 
	 * @param title
	 *            the title
	 */
	public HtmlTemplate(String title) {
		this.title = title;
	}

	/**
	 * Creates a new instance without a title
	 */
	public HtmlTemplate() {
		this(null);
	}

	/**
	 * Build the HTML structure
	 * 
	 * @param body
	 *            the body HTML node
	 */
	protected abstract void build(MarkupTag body);

	/**
	 * Replace the variables in the template
	 * 
	 * @param template
	 *            the generated template HTML
	 * @return the HTML with variables replaced
	 */
	public String replace(String template) {
		for (final Entry<String, String> variable : variables.entrySet()) {
			template = template.replaceAll(
					Pattern.quote("${" + variable.getKey() + "}"),
					variable.getValue());
		}
		return template;
	}

	/**
	 * Register an variable for this template
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public void register(String name, String value) {
		variables.put(name, value);
	}

	/**
	 * Creates a new {@link Html} object using the {@link HtmlTemplate} provided
	 * in the constructor. This method can be invoked multiple times.
	 * 
	 * @return the {@link Html} object
	 */
	public Html build() {
		final Html html = new L2HtmlTag();
		if (title != null) {
			final HeadTag head = new L2HeadTag();
			final TitleTag title = new L2TitleTag();
			html.getChildren().add(head);
			head.getChildren().add(title);
			title.getChildren().add(new TextNode(this.title));
		}
		final Tag body = new L2BodyTag();
		html.getChildren().add(body);

		this.build(new MarkupTag(body));
		return html;
	}

	/**
	 * Generates the HTML code for this template. It also replaces the
	 * variables.
	 * 
	 * @return the HTML code
	 */
	public String toHtmlString() {
		return this.replace(build().toHtml());
	}
}
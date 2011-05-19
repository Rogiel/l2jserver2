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

import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.HeadTag;
import org.htmlparser.tags.Html;
import org.htmlparser.tags.TitleTag;

import com.l2jserver.util.html.L2HeadTag;
import com.l2jserver.util.html.L2HtmlTag;
import com.l2jserver.util.html.L2TitleTag;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class Markup {
	private final Builder builder;
	private final String title;

	public Markup(String title, Builder builder) {
		this.builder = builder;
		this.title = title;
	}

	public Html build() {
		final Html html = new L2HtmlTag();
		final HeadTag head = new L2HeadTag();
		final TitleTag title = new L2TitleTag();

		head.getChildren().add(head);
		head.getChildren().add(title);
		title.getChildren().add(new TextNode(this.title));

		this.builder.build(new MarkupTag(html));
		return html;
	}

	public interface Builder {
		void build(MarkupTag body);
	}
}

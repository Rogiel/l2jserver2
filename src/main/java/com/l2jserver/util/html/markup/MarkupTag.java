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

import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;

import com.l2jserver.util.html.L2BrTag;
import com.l2jserver.util.html.L2CenterTag;
import com.l2jserver.util.html.L2DivTag;
import com.l2jserver.util.html.L2FontTag;
import com.l2jserver.util.html.L2ImageTag;
import com.l2jserver.util.html.L2LinkTag;
import com.l2jserver.util.html.L2NewLineTag;

/**
 * This is an helper class that helps creating new tags in the HTML document.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MarkupTag {
	/**
	 * The current tag
	 */
	private final Tag tag;
	/**
	 * The parent {@link MarkupTag}
	 */
	private final MarkupTag parent;

	/**
	 * Creates a new instance with a parent
	 * 
	 * @param tag
	 *            the tag
	 * @param parent
	 *            the parent
	 */
	public MarkupTag(Tag tag, MarkupTag parent) {
		this.tag = tag;
		this.parent = parent;
	}

	/**
	 * Creates a new instance without a parent
	 * 
	 * @param tag
	 *            the tag
	 */
	public MarkupTag(Tag tag) {
		this(tag, null);
	}

	/**
	 * Adds an attribute to the current tag
	 * 
	 * @param name
	 *            the attribute name
	 * @param value
	 *            the attribute value
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag attr(String name, String value) {
		tag.setAttribute(name, value);
		return this;
	}

	/**
	 * Adds a plain text to the tag. It will not use any formatting.
	 * 
	 * @param text
	 *            the text
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag text(String text) {
		tag.getChildren().add(new TextNode(text));
		return this;
	}

	/**
	 * Adds an internal text from an code
	 * 
	 * @param code
	 *            the text code
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag textcode(int code) {
		return text("&$" + Integer.toString(code) + ";");
	}

	/**
	 * Adds a plain text to the tag, use the color formatting.
	 * 
	 * @param text
	 *            the text
	 * @param color
	 *            the text color
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag text(String text, String color) {
		final Tag font = new L2FontTag();
		font.setAttribute("color", color);
		font.getChildren().add(new TextNode(text));
		this.tag.getChildren().add(font);
		return this;
	}

	/**
	 * Creates a new DIV element
	 * 
	 * @return this div {@link MarkupTag}
	 */
	public MarkupTag div() {
		final Tag tag = new L2DivTag();
		this.tag.getChildren().add(tag);
		return new MarkupTag(tag, this);
	}

	/**
	 * Inserts a line break
	 * 
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag br() {
		final Tag tag = new L2BrTag();
		this.tag.getChildren().add(tag);
		return this;
	}

	/**
	 * Inserts a new paragraph
	 * 
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag p() {
		final Tag tag = new L2NewLineTag();
		this.tag.getChildren().add(tag);
		return this;
	}

	/**
	 * Creates a new CENTER element. Text inside this element will be centered.
	 * 
	 * @return this CENTER {@link MarkupTag}
	 */
	public MarkupTag center() {
		final Tag tag = new L2CenterTag();
		this.tag.getChildren().add(tag);
		return new MarkupTag(this.tag, this);
	}

	/**
	 * Adds an image
	 * 
	 * @param src
	 *            the image source
	 * @param height
	 *            the height
	 * @param width
	 *            the width
	 * @return this {@link MarkupTag}
	 */
	public MarkupTag addImage(String src, int height, int width) {
		final Tag tag = new L2ImageTag();
		this.tag.getChildren().add(tag);
		tag.setAttribute("height", Integer.toString(height));
		tag.setAttribute("width", Integer.toString(width));
		tag.setAttribute("src", src);
		return this;
	}

	/**
	 * Adds a link
	 * 
	 * @param text
	 *            the link text
	 * @param action
	 *            the link action (will automatically append "bypass -h")
	 * @return the MarkupTag for chained calls
	 */
	public MarkupTag addLink(String text, String action) {
		final Tag tag = new L2LinkTag();
		this.tag.getChildren().add(tag);
		tag.setAttribute("action", "bypass -h " + action);
		tag.getChildren().add(new TextNode(text));
		return this;
	}

	/**
	 * Close this tag and return the parent element
	 * 
	 * @return the parent element, if any.
	 */
	public MarkupTag close() {
		return parent;
	}

	/**
	 * @return the tag
	 */
	public Tag getTag() {
		return tag;
	}

	/**
	 * @return the parent
	 */
	public MarkupTag getParent() {
		return parent;
	}
}

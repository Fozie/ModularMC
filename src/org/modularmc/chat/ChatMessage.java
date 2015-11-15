package org.modularmc.chat;

import org.json.simple.JSONObject;

/**
 * @author Caspar Norée Palm
 */
public class ChatMessage {
	String text;
	boolean bold, italic, underlined, strikethrough, obfuscated;
	ChatColor color;
	
	public ChatMessage(String text, boolean bold, boolean italic,
			boolean underlined, boolean strikethrough, boolean obfuscated,
			ChatColor color) {
		this.text = text;
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.strikethrough = strikethrough;
		this.obfuscated = obfuscated;
		this.color = color;
	}
	
	
	public ChatMessage(String text) {
		this(text, false, false, false, false, false, ChatColor.RESET);
	}

	public String getJSON() {
		JSONObject json = new JSONObject();
		json.put("text", text);
		json.put("bold", text);
		json.put("italic", text);
		json.put("underlined", text);
		json.put("strikethrough", text);
		json.put("obfuscated", text);
		json.put("color", color.name().toLowerCase());
		return json.toJSONString();
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}


	/**
	 * @return the bold
	 */
	public boolean isBold() {
		return bold;
	}


	/**
	 * @param bold the bold to set
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}


	/**
	 * @return the italic
	 */
	public boolean isItalic() {
		return italic;
	}


	/**
	 * @param italic the italic to set
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}


	/**
	 * @return the underlined
	 */
	public boolean isUnderlined() {
		return underlined;
	}


	/**
	 * @param underlined the underlined to set
	 */
	public void setUnderlined(boolean underlined) {
		this.underlined = underlined;
	}


	/**
	 * @return the strikethrough
	 */
	public boolean isStrikethrough() {
		return strikethrough;
	}


	/**
	 * @param strikethrough the strikethrough to set
	 */
	public void setStrikethrough(boolean strikethrough) {
		this.strikethrough = strikethrough;
	}


	/**
	 * @return the obfuscated
	 */
	public boolean isObfuscated() {
		return obfuscated;
	}


	/**
	 * @param obfuscated the obfuscated to set
	 */
	public void setObfuscated(boolean obfuscated) {
		this.obfuscated = obfuscated;
	}


	/**
	 * @return the color
	 */
	public ChatColor getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(ChatColor color) {
		this.color = color;
	}
	
}

package org.agtsys.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EasyUINode {
	private Long id;
	private String text;
	private String state = "open"; //'open' or 'closed' 
	private boolean checked;
	private Map<String,Object> attributes;
	private List<EasyUINode> children = new ArrayList<EasyUINode>();
	public Long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public String getState() {
		return state;
	}
	public boolean isChecked() {
		return checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public List<EasyUINode> getChildren() {
		return children;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public void setChildren(List<EasyUINode> children) {
		this.children = children;
	}
}

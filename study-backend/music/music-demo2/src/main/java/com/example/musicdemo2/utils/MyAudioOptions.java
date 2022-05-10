package com.example.musicdemo2.utils;

import java.util.HashMap;
import java.util.Map;

public class MyAudioOptions {
	
	private String cmd = "ffmpeg -i ";
	
	private String src;
	
	
	private String dest;
	
	
	private Map<String, Object> options;
	
	public MyAudioOptions(Map<String, Object> options) {
		this.options = options;
	}
	
	
	public String getCmd() {
		return cmd;
	}
	
	public MyAudioOptions setCmd(String cmd) {
		this.cmd = cmd;
		return this;
	}
	
	public String getSrc() {
		return src;
	}
	
	public MyAudioOptions setSrc(String src) {
		this.src = src;
		return this;
	}
	
	public String getDest() {
		return dest;
	}
	
	public MyAudioOptions setDest(String dest) {
		this.dest = dest;
		return this;
	}
	
	public Map<String, Object> getOptions() {
		return options;
	}
	
	
	public MyAudioOptions addOption(String conf, Object value) {
		options.put("-" + conf, value);
		return this;
	}
	
	
	public String build() {
		StringBuilder builder = new StringBuilder(this.cmd);
		builder.append(" ").append(this.src);
		
		for (Map.Entry<String, Object> entry : options.entrySet()) {
			builder.append(entry.getKey().startsWith("-") ? " " : " -")
					.append(entry.getKey())
					.append(" ").append(entry.getValue());
		}
		
		builder.append(" ").append(this.dest);
		return builder.toString();
	}
}
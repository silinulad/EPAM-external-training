package com.example.intouch.files.utils.beans;

public class AddressPartHeader {

	private final int startIndex;
	private final int lastIndex;

	public AddressPartHeader(int startIndex, int lastIndex) {
		this.startIndex = startIndex;
		this.lastIndex = lastIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}
}

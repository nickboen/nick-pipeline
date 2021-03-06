package org.gradle.example;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		sb.append("sku", sku);

		return sb.toString();

	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}

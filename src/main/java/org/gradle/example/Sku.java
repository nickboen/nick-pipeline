package org.gradle.example;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Sku implements Serializable {

	private static final long serialVersionUID = 1L;

	private String skuId;

	private double price;

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		sb.append("skuId", skuId);
		sb.append("price", price);

		return sb.toString();

	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

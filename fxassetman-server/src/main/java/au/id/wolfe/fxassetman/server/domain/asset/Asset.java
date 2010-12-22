/**
 *
 * Copyright (C) 2010 markw <mark@wolfe.id.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.id.wolfe.fxassetman.server.domain.asset;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import au.id.wolfe.fxassetman.server.domain.PersistableEntity;
import au.id.wolfe.fxassetman.server.domain.company.Client;
import au.id.wolfe.fxassetman.server.domain.company.Manufacturer;
import au.id.wolfe.fxassetman.server.domain.company.Supplier;

/**
 * Represents a fixed asset.
 */
@Entity
@Table(name = "assets")
@SQLDelete(sql = "UPDATE customer SET deleted = '1' WHERE id = ?")
@Where(clause = "deleted <> '1'")
public class Asset extends PersistableEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "ASSET_TAG", unique = true)
	private String assetTag;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "MODEL_NAME")
	private String modelName;

	@Size(min = 1, max = 255)
	@Column(name = "MODEL_NO")
	private String modelNo;

	@Column(name = "PURCHASE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;

	@Column(name = "PURCHASE_PRICE")
	private Double purchasePrice;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "SERIAL_NO")
	private String serialNo;

	@Column(name = "ASSET_STATUS")
	private AssetStatus assetStatus;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "ASSET_TYPE_ID", nullable = false)
	private AssetType assetType;

	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "MANUFACTURER_ID")
	private Manufacturer manufacturer;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private Client client;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "SUPPLIER_ID", nullable = false)
	private Supplier supplier;

	public Asset() {
	}

	public String getAssetTag() {
		return assetTag;
	}

	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public AssetStatus getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(AssetStatus assetStatus) {
		this.assetStatus = assetStatus;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[] {
				"deleted", "dateUpdated", "dateAdded" });
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

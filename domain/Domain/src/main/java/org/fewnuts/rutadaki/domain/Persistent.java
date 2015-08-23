package org.fewnuts.rutadaki.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class Persistent implements Serializable {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = -5833127456445521624L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	@Version
	@Column(name = "version")
	private int version = 0;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update")
	private Date lastUpdate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Persistent)) {
			return false;
		}
		final Persistent other = (Persistent) obj;

		if (this.id == null || other.id == null) {
			return false;
		}
		if (this.id != null && other.id != null) {
			if (this.id != other.id) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the DataBase identifier of the object
	 * 
	 * @return the DataBase identfier of the object
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Updates the identifier
	 * 
	 * @param id
	 *            new identifier
	 */
	protected void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	@SuppressWarnings("unused")
	private void setVersion(final int version) {
		this.version = version;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}

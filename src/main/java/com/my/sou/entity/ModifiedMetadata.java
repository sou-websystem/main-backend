package com.my.sou.entity;

import java.time.Instant;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ModifiedMetadata extends CreatedMetadata {

  @LastModifiedBy
  @Column(name = "MODIFIED_BY", nullable = false)
  private String modifiedBy;

  @LastModifiedDate
  @Column(name = "MODIFIED_ON")
  private Instant modifiedOn;

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Instant getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(Instant modifiedOn) {
    this.modifiedOn = modifiedOn;
  }

}

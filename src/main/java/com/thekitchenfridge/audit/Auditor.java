package com.thekitchenfridge.audit;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
public abstract class Auditor <U> {

    @ToString.Exclude
    @CreatedBy
    protected U createdBy;

    @ToString.Exclude
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;

    @ToString.Exclude
    @LastModifiedBy
    protected U lastModifiedBy;

    @ToString.Exclude
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;
}

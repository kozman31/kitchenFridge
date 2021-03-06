package com.thekitchenfridge.audit;

import com.thekitchenfridge.security.entities.LoginAttempt;
import com.thekitchenfridge.security.entities.LoginAttemptHistory;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.thekitchenfridge.audit.Action.INSERT;
import static com.thekitchenfridge.audit.Action.UPDATE;

public class LoginAttemptHistoryListener {

    @PreUpdate
    public void preUpdate(LoginAttempt target){perform(target, INSERT);}

    @PrePersist
    public void prePersist(LoginAttempt target){perform(target, UPDATE);}

    @Transactional
    public void perform(LoginAttempt target, Action action) {
        EntityManager entityManager = BeanUtility.getBean(EntityManager.class);
        entityManager.persist(new LoginAttemptHistory(target));
    }
}

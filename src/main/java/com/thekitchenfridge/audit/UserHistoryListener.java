package com.thekitchenfridge.audit;

import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserHistory;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.thekitchenfridge.audit.Action.*;

public class UserHistoryListener {

    @PreUpdate
    public void preUpdate(User target){perform(target, UPDATE);}

    @PrePersist
    public void prePersist(User target){perform(target, INSERT);}

    @PreRemove
    public void preRemove(User target){ perform(target, DELETE);}

    @Transactional
    public void perform(User target, Action action) {
        EntityManager entityManager = BeanUtility.getBean(EntityManager.class);
        entityManager.persist(new UserHistory(target, action));
    }
}

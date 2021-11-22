package com.ekb.hangmanhero.login.repository;

import java.util.List;
import java.util.Optional;

import com.ekb.hangmanhero.login.model.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    Optional<SystemUser> findByLoginAndDeletedIsFalse(String login);

    Optional<SystemUser> findByResetPasswordTokenAndDeletedIsFalse(String resetToken);

    List<SystemUser> findByDeleted(Boolean deleted);
}
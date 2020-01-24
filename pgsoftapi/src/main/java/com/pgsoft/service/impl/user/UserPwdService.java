package com.pgsoft.service.impl.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserPwd;
import com.pgsoft.persistence.impl.jpa.querydsl.UserPwdQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.UserPwdRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.dto.UserPwdDTO;
import com.pgsoft.service.impl.user.mapper.UserPwdMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class UserPwdService extends PgSoftChildServiceAdapter<UserPwd, UserPwdDTO, User> implements UserDetailsService {
    public UserPwdService(UserPwdMapper mapper
            , UserPwdRepository repository
            , UserPwdQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<UserPwdDTO>> create(@Nullable Long parentId, @NotNull UserPwdDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<UserPwdDTO>> update(@Nullable Long parentId, @Nullable UserPwdDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return ((UserPwdRepository) this.getRepository()).findByUserName(email).orElseThrow(NotFoundException::new);
    }
}

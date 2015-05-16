package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.PasswordResetTokenData;
import br.edu.utfpr.labscontrol.model.entity.PasswordResetToken;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by EdsonGustavo on 15/05/2015.
 */
@Service
public class PasswordResetTokenServiceImpl extends CrudService<PasswordResetToken, Integer> implements PasswordResetTokenService {
    @Autowired
    PasswordResetTokenData passwordResetTokenData;

    @Override
    protected JpaRepository<PasswordResetToken, Integer> getData() {
        return this.passwordResetTokenData;
    }

    public PasswordResetToken findByToken(String token) {
        return this.passwordResetTokenData.findByToken(token);
    }
}

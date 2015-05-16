package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.PasswordResetToken;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

/**
 * Created by EdsonGustavo on 15/05/2015.
 */
public interface PasswordResetTokenService extends ICrudService<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);
}

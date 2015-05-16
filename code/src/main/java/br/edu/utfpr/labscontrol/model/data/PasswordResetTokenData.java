package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EdsonGustavo on 15/05/2015.
 */
public interface PasswordResetTokenData extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);
}

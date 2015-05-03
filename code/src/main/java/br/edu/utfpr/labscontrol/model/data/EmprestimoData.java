package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
public interface EmprestimoData extends JpaRepository<Emprestimo, Integer> {
}

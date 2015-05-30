package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.EmprestimoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
public interface EmprestimoItemData extends JpaRepository<EmprestimoItem, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM EmprestimoItem e WHERE e.id = :id")
    void deleteEmprestimoItemById(@Param("id") Integer id);
}

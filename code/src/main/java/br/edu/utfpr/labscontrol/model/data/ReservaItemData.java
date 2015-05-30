package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.ReservaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by EdsonGustavo on 29/03/2015.
 */
public interface ReservaItemData extends JpaRepository<ReservaItem, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ReservaItem r WHERE r.id = :id")
    void deleteReservaItemById(@Param("id") Integer id);
}

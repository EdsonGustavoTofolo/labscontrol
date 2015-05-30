package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ReservaItemData;
import br.edu.utfpr.labscontrol.model.entity.ReservaItem;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ReservaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by EdsonGustavo on 29/03/2015.
 */
@Service
public class ReservaItemServiceImpl extends CrudService<ReservaItem, Integer> implements ReservaItemService {
    @Autowired
    private ReservaItemData reservaItemData;

    @Override
    protected JpaRepository<ReservaItem, Integer> getData() {
        return reservaItemData;
    }

    @Override
    public void deleteReservaItemById(Integer id) {
        reservaItemData.deleteReservaItemById(id);
    }
}

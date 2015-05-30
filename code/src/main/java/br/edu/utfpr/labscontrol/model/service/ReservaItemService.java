package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.ReservaItem;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

/**
 * Created by EdsonGustavo on 29/03/2015.
 */
public interface ReservaItemService extends ICrudService<ReservaItem, Integer> {
    void deleteReservaItemById(Integer id);
}

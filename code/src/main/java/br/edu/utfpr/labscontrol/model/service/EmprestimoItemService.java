package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.EmprestimoItem;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import org.springframework.data.repository.query.Param;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
public interface EmprestimoItemService extends ICrudService<EmprestimoItem, Integer> {
    void deleteEmprestimoItemById(Integer id);
}

package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.EmprestimoItemData;
import br.edu.utfpr.labscontrol.model.entity.EmprestimoItem;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.EmprestimoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
@Service
public class EmprestimoItemServiceImpl extends CrudService<EmprestimoItem, Integer> implements EmprestimoItemService {
    @Autowired
    private EmprestimoItemData emprestimoItemData;

    @Override
    protected JpaRepository<EmprestimoItem, Integer> getData() {
        return emprestimoItemData;
    }
}

package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.MarcaMaterialData;
import br.edu.utfpr.labscontrol.model.entity.MarcaMaterial;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaMaterialService;
import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class MarcaMaterialServiceImpl extends CrudService<MarcaMaterial, Integer> implements MarcaMaterialService {
    @Autowired
    private MarcaMaterialData marcaMaterialData;

    @Override
    protected JpaRepository<MarcaMaterial, Integer> getData() {
        return this.marcaMaterialData;
    }

    @Override
    @Cacheable(cacheName = "marca")
    @Transactional(readOnly = true)
    public List<MarcaMaterial> findByNomeContaining(String nome) {
        return this.marcaMaterialData.findByNomeContaining(nome);
    }

    //@Override
    //@TriggersRemove(cacheName = "marca") remove o cache
    //public Marca save(Marca entity) throws Exception {
    //    return super.save(entity);
    //}
}

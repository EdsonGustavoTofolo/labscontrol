package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.MarcaData;
import br.edu.utfpr.labscontrol.model.entity.Marca;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaService;
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
public class MarcaServiceImpl extends CrudService<Marca, Integer> implements MarcaService {
    @Autowired
    private MarcaData marcaData;

    @Override
    protected JpaRepository<Marca, Integer> getData() {
        return this.marcaData;
    }

    @Override
    @Cacheable(cacheName = "marca")
    @Transactional(readOnly = true)
    public List<Marca> findByNomeContaining(String nome) {
        return this.marcaData.findByNomeContaining(nome);
    }

    //@Override
    //@TriggersRemove(cacheName = "marca") remove o cache
    //public Marca save(Marca entity) throws Exception {
    //    return super.save(entity);
    //}
}

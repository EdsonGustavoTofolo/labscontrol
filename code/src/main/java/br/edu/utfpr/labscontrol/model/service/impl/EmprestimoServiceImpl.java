package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.EmprestimoData;
import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class EmprestimoServiceImpl extends CrudService<Emprestimo, Integer> implements EmprestimoService {
    @Autowired
    private EmprestimoData emprestimoData;

    @Override
    protected JpaRepository<Emprestimo, Integer> getData() {
        return this.emprestimoData;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByAmbiente(Ambiente ambiente) {
        return this.emprestimoData.findByAmbiente(ambiente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByFromUsuario(Usuario usuario) {
        return this.emprestimoData.findByFromUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByToUsuario(Usuario usuario) {
        return this.emprestimoData.findByToUsuario(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByMaterial(Material material) {
        return this.emprestimoData.findByMaterial(material);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByAmbienteAndFromUsuario(Ambiente ambiente, Usuario usuario) {
        return this.emprestimoData.findByAmbienteAndFromUsuario(ambiente, usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByAmbienteAndToUsuario(Ambiente ambiente, Usuario usuario) {
        return this.emprestimoData.findByAmbienteAndToUsuario(ambiente, usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Emprestimo> findByAmbienteAndFromUsuarioAndMaterial(Ambiente ambiente, Usuario usuario, Material material) {
        return this.emprestimoData.findByAmbienteAndFromUsuarioAndMaterial(ambiente, usuario, material);
    }
}

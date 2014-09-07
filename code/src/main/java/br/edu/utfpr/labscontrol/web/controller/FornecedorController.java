package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ContatoService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class FornecedorController extends CrudController<Fornecedor, Integer> {

    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ContatoService contatoService;

    private Contato contato;

    @Override
    protected ICrudService<Fornecedor, Integer> getService() {
        return this.fornecedorService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/fornecedor/fornecedorForm.xhtml?faces-redirect=true";
    }

    public void novoContato() {
        this.contato = new Contato();
        this.contato.setFornecedor(this.entity);
    }

    public void saveContato() {
        try {
            this.contatoService.save(this.contato);
            this.entity.setContatos(this.contatoService.findByFornecedor(this.entity));
            addMessage("Contato salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void deleteContato() {
        try {
            this.entity.getContatos().remove(this.contato);
            save();
            this.entity.getContatos();
            addMessage("Contato removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public Contato getEntityEmbedded() {
        return contato;
    }

    public void setEntityEmbedded(Contato contato) {
        this.contato = contato;
    }
}

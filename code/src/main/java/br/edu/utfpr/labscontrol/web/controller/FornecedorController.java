package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.enumeration.TiposDeContatoEnum;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ContatoService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.EnumUtil;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.EventListener;
import java.util.List;

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

    private SelectItem[] tiposDeContatos;
    private Contato contato;
    private String mascara;

    @Override
    protected ICrudService<Fornecedor, Integer> getService() {
        return this.fornecedorService;
    }

    @Override
    protected void inicializar() {
        tiposDeContatos = EnumUtil.populaSelectTiposDeContatos(TiposDeContatoEnum.values());
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
            entity.getContatos().remove(this.contato);
            save();
            entity.setContatos(contatoService.findByFornecedor(this.entity));
            addMessage("Contato removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void onChangeTipoDeContato(ValueChangeEvent e) {
        Enum obj = (Enum) e.getNewValue();
        if (obj.ordinal() == 1 || obj.ordinal() == 3) {
            this.mascara = "(99) 9999-9999?9";
        } else {
            this.mascara = "";
        }
    }

    public Contato getEntityEmbedded() {
        return contato;
    }

    public void setEntityEmbedded(Contato contato) {
        this.contato = contato;
    }

    public SelectItem[] getTiposDeContatos() {
        return tiposDeContatos;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }
}

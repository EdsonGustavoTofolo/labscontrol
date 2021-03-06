package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.entity.Cidade;
import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.enumeration.TiposDeContatoEnum;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CidadeService;
import br.edu.utfpr.labscontrol.model.service.ContatoService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.CepWebService;
import br.edu.utfpr.labscontrol.web.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class FornecedorController extends CrudController<Fornecedor, Integer> {
    @Autowired private FornecedorService fornecedorService;
    @Autowired private ContatoService contatoService;
    @Autowired private CidadeService cidadeService;
    private SelectItem[] tiposDeContatos;
    private Contato contato;
    private String mascara;

    @Override
    protected ICrudService<Fornecedor, Integer> getService() {
        return this.fornecedorService;
    }

    @Override
    protected void inicializar() {
        tiposDeContatos = EnumUtil.populaSelect(TiposDeContatoEnum.values());
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/fornecedor/fornecedorForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/fornecedor/fornecedorSearch.xhtml?faces-redirect=true";
    }

    public void novoContato() {
        this.contato = new Contato();
        this.contato.setFornecedor(this.entity);
    }

    public List<Cidade> completeCidade(String nome) {
        return cidadeService.findByNomeContaining(nome);
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
            contatoService.deleteById(this.contato.getId());
            entity.getContatos();
            addMessage("Contato removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void onChangeTipoDeContato(ValueChangeEvent e) {
        Enum obj = (Enum) e.getNewValue();
        if (obj.ordinal() == TiposDeContatoEnum.CELULAR.ordinal() ||
            obj.ordinal() == TiposDeContatoEnum.TELEFONE.ordinal()) {
            this.mascara = "(99) 9999-9999?9";
        } else {
            this.mascara = "";
        }
    }

    public void handleKeyEvent() {
        if (entity.getCep().matches("\\d{2}.\\d{3}-\\d{3}")) {
            CepWebService cepWebService = new CepWebService(entity.getCep());

            if (cepWebService.getResultado() > 0) {
                entity.setLogradouro(cepWebService.getLogradouro());
                List<Cidade> cidades = cidadeService.findByNomeContaining(cepWebService.getCidade());
                Cidade cidade = null;
                if (cidades != null && cidades.size() > 0) {
                    cidade = cidades.get(0);
                }
                entity.setCidade(cidade);
                entity.setBairro(cepWebService.getBairro());
            } else {
                entity.setLogradouro("");
                entity.setCidade(null);
                entity.setBairro("");
                addMessage("Servidor não está respondendo ou CEP não localizado!", FacesMessage.SEVERITY_ERROR);
            }
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

package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class MaterialDeConsumoController extends CrudController<MaterialDeConsumo, Integer> {

    private BigDecimal quantidade;

    @Override
    public void init() {
        super.init();
        this.quantidade = BigDecimal.ZERO;
    }

    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;

    @Override
    protected ICrudService<MaterialDeConsumo, Integer> getService() {
        return this.materialDeConsumoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/materialdeconsumo/materialDeConsumoForm.xhtml?faces-redirect=true";
    }

    public void addQtdAction(ActionEvent actionEvent) {
        UIParameter o =  (UIParameter)actionEvent.getComponent().findComponent("qtdParam");
        o.getValue().toString();
        BigDecimal t = new BigDecimal(actionEvent.getSource().toString());
        BigDecimal soma =  entity.getQtdAtual().add(this.quantidade, MathContext.DECIMAL32);
        entity.setQtdAtual(soma);
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}

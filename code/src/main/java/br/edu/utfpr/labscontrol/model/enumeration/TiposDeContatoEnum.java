package br.edu.utfpr.labscontrol.model.enumeration;

/**
 * Created by EdsonGustavo on 12/09/2014.
 */
public enum TiposDeContatoEnum implements EnumLabel {

    EMAIL("Email"), CELULAR("Celular"), PAGINAWEB("Página web"), TELEFONE("Telefone"), FAX("Fax"), OUTRO("Outro");

    private String label;

    TiposDeContatoEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}

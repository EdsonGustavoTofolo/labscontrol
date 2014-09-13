package br.edu.utfpr.labscontrol.model.enumeration;

/**
 * Created by EdsonGustavo on 12/09/2014.
 */
public enum TiposDeContatoEnum implements EnumLabel {

    Email("Email"), Celular("Celular"), PaginaWeb("Página web"), Telefone("Telefone"), Fax("Fax"), Outro("Outro");

    private String label;

    TiposDeContatoEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}

package br.edu.utfpr.labscontrol.model.enumeration;

/**
 * Created by EdsonGustavo on 01/05/2015.
 */
public enum RolesEnum implements EnumLabel {

    ADM("Administrador"), USER("Professor"), ATENDENTE("Atendente");

    private String label;

    RolesEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}

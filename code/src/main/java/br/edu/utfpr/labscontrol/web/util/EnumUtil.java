/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.labscontrol.web.util;

import br.edu.utfpr.labscontrol.model.enumeration.EnumLabel;

import javax.faces.model.SelectItem;


public class EnumUtil {
    
    public static SelectItem[] populaSelectTiposDeContatos(Object[] values) {
        SelectItem[] itens = new SelectItem[values.length];
        int i = 0;
        for (Object value : values) {
            EnumLabel item = (EnumLabel) value;
            itens[i++] = new SelectItem(item, String.valueOf(item.getLabel()));
        }
        return itens;
    }
    
}

package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface EmprestimoService extends ICrudService<Emprestimo, Integer> {
    /**
     * Obtém todos os empréstimos vinculados ao ambiente passado por parâmetro
     * @param ambiente
     * @return
     */
    List<Emprestimo> findByAmbiente(Ambiente ambiente);

    /**
     * Obtém todos os empréstimos vinculados ao usuário que o cadastrou passado por parâmetro
     * @param usuario
     * @return
     */
    List<Emprestimo> findByFromUsuario(Usuario usuario);

    /**
     * Obtém todos os empréstimos vinculados ao usuário que o levou passado por parâmetro
     * @param usuario
     * @return
     */
    List<Emprestimo> findByToUsuario(Usuario usuario);

    /**
     * Obtém todos os empréstimos vinculados ao material passado por parâmetro
     * @param material
     * @return
     */
    List<Emprestimo> findByMaterial(Material material);

    /**
     * Obtém todos os empréstimos vinculados ao ambiente e usuário que o cadastrou passados por parâmetros
     * @param ambiente
     * @param usuario
     * @return
     */
    List<Emprestimo> findByAmbienteAndFromUsuario(Ambiente ambiente, Usuario usuario);

    /**
     * Obtém todos os empréstimos vinculados ao ambiente e usuário que o levou passados por parâmetros
     * @param ambiente
     * @param usuario
     * @return
     */
    List<Emprestimo> findByAmbienteAndToUsuario(Ambiente ambiente, Usuario usuario);

    /**
     * Obtém todos os empréstimos vinculados ao ambiente e usuário que o cadastrou e material passados por parâmetros
     * @param ambiente
     * @param usuario
     * @param material
     * @return
     */
    List<Emprestimo> findByAmbienteAndFromUsuarioAndMaterial(Ambiente ambiente, Usuario usuario, Material material);

    //TODO os find por datas de devolução de empréstimo
}

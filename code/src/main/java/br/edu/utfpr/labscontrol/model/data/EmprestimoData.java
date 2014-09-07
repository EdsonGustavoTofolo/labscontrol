package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface EmprestimoData  extends JpaRepository<Emprestimo, Integer> {
    List<Emprestimo> findByAmbiente(Ambiente ambiente);

    List<Emprestimo> findByFromUsuario(Usuario usuario);

    List<Emprestimo> findByToUsuario(Usuario usuario);

    List<Emprestimo> findByMaterial(Material material);

    List<Emprestimo> findByAmbienteAndFromUsuario(Ambiente ambiente, Usuario usuario);

    List<Emprestimo> findByAmbienteAndToUsuario(Ambiente ambiente, Usuario usuario);

    List<Emprestimo> findByAmbienteAndFromUsuarioAndMaterial(Ambiente ambiente, Usuario usuario, Material material);
}

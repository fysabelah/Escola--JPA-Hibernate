package br.school.ctrl.negocio;

import br.school.ctrl.exception.MatriculaException;
import br.school.model.dao.MatriculaDAO;
import br.school.model.entities.Matricula;

import java.util.List;

public class MatriculaNegocio {

    private final MatriculaDAO matriculaDAO = new MatriculaDAO();

    public Matricula inserir(Matricula matricula) throws MatriculaException {
        this.validarMatricula(matricula);

        return matriculaDAO.inserir(matricula);
    }

    public List<Matricula> buscaTodos() throws MatriculaException {
        List<Matricula> matriculas = matriculaDAO.buscarTodos();

        if (matriculas == null || matriculas.isEmpty()) {
            throw new MatriculaException("Não existem matrículas cadastradas");
        }

        return matriculas;
    }

    public Matricula buscaPorId(Integer id) throws MatriculaException {
        Matricula matricula = matriculaDAO.buscarId(id);

        if (matricula == null) {
            throw new MatriculaException("Matrícula não encontrada!");
        }

        return matricula;
    }

    public Matricula alterar(Matricula matricula) throws MatriculaException {
        this.validarMatricula(matricula);

        buscaPorId(matricula.getIdMatricula());

        return matriculaDAO.atualizar(matricula);
    }

    public void excluir(Integer id) throws MatriculaException {
        Matricula matricula = buscaPorId(id);

        matriculaDAO.excluir(matricula);
    }

    private void validarMatricula(Matricula matricula) throws MatriculaException {
        if (matricula.getAluno() == null || matricula.getOferta() == null) {
            throw new MatriculaException("Cadastro de Aluno e/ou Oferta pendente(s).");
        }

        if (!matricula.getOferta().isOfertaValida()) {
            throw new MatriculaException("A oferta não está válida para realização de matrícula!");
        }
    }
}

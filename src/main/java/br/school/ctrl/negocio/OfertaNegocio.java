package br.school.ctrl.negocio;

import br.school.ctrl.exception.OfertaException;
import br.school.model.dao.OfertaDAO;
import br.school.model.entities.Oferta;

import java.util.List;

public class OfertaNegocio {

    private final OfertaDAO ofertaDAO = new OfertaDAO();

    public Oferta inserir(Oferta oferta) throws OfertaException {
        this.validarOferta(oferta);

        return ofertaDAO.inserir(oferta);
    }

    public List<Oferta> buscaTodos() throws OfertaException {
        List<Oferta> ofertas = ofertaDAO.buscarTodos();

        if (ofertas == null || ofertas.isEmpty()) {
            throw new OfertaException("Não existem ofertas cadastradas");
        }

        return ofertas;
    }

    public Oferta buscaPorId(Integer id) throws OfertaException {
        Oferta oferta = ofertaDAO.buscarId(id);

        if (oferta == null) {
            throw new OfertaException("Oferta não encontrada!");
        }

        return oferta;
    }

    public Oferta alterar(Oferta oferta) throws OfertaException {
        this.validarOferta(oferta);

        buscaPorId(oferta.getIdOferta());

        return ofertaDAO.atualizar(oferta);
    }

    public void excluir(Integer id) throws OfertaException {
        Oferta oferta = buscaPorId(id);

        ofertaDAO.excluir(oferta);
    }

    private void validarOferta(Oferta oferta) throws OfertaException {
        if (oferta.getDisciplina() == null) {
            throw new OfertaException("É necessário que a disciplina seja informada para cadastro da oferta!");
        }

        if (oferta.getProfessor() == null) {
            throw new OfertaException("É necessário que uma oferta tenha um(a) professor(a) associado(a)!");
        }
    }
}

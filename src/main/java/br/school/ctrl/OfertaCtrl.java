package br.school.ctrl;

import br.school.ctrl.exception.OfertaException;
import br.school.ctrl.negocio.OfertaNegocio;
import br.school.model.entities.Oferta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OfertaCtrl {

    private final OfertaNegocio negocio = new OfertaNegocio();

    private static final Logger LOGGER = Logger.getLogger(OfertaCtrl.class.getName());

    public Oferta inserir(Oferta oferta) {
        try {
            oferta = negocio.inserir(oferta);
            LOGGER.log(Level.INFO, "Oferta cadastrada com sucesso: {0}", oferta);
        } catch (OfertaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar cadastrar oferta: {0}", e.getMessage());
        }

        return oferta;
    }

    public List<Oferta> buscaTodos() {
        List<Oferta> ofertas = new ArrayList<>();

        try {
            ofertas = negocio.buscaTodos();
        } catch (OfertaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar buscar as ofertas cadastradas: {0}", e.getMessage());
        }

        return ofertas;
    }

    public Oferta buscaPorId(Integer id) {
        Oferta oferta = null;

        try {
            oferta = negocio.buscaPorId(id);
        } catch (OfertaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar buscar oferta: {0}", e.getMessage());
        }

        return oferta;
    }

    public Oferta alterar(Oferta oferta) {
        try {
            oferta = negocio.alterar(oferta);
            LOGGER.log(Level.INFO, "Oferta alterado com sucesso: {0}", oferta);
        } catch (OfertaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar alterar oferta: {0}", e.getMessage());
        }
        return oferta;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Oferta excluído com sucesso!");
        } catch (OfertaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar excluir o oferta: {0}", e.getMessage());
        }
    }
}

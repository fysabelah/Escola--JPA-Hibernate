package br.ufg.inf.ctrl.negocio;

import java.util.List;

import br.ufg.inf.ctrl.exception.OfertaExection;
import br.ufg.inf.model.dao.OfertaDAO;
import br.ufg.inf.model.entities.Oferta;

public class OfertaNegocio {


		OfertaDAO dao = new OfertaDAO();
		ProfessorNegocio professorNegocio = new ProfessorNegocio();
		DisciplinaNegocio disciplinaNegocio = new DisciplinaNegocio();
	
		public Oferta inserir(Oferta oferta) throws OfertaExection {
			this.validarOferta(oferta);
			return dao.inserir(oferta);
		}
		
		public List<Oferta> buscaTodos() throws OfertaExection{
			return dao.buscaTodos();
		}
		
		public Oferta buscaPorId(Integer id) throws OfertaExection  {
			return dao.buscaPorId(id);
		}
		

		public Oferta alterar(Oferta oferta) throws OfertaExection {		
			this.validarOferta(oferta);
			return dao.alterar(oferta);
		}
		
		public void excluir(Integer id) throws OfertaExection {
			dao.excluir(id);
		}
		
		private void validarOferta(Oferta oferta) throws OfertaExection {
			

		}
}

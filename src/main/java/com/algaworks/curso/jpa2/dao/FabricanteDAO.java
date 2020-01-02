package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable{
	
	@Inject
	private EntityManager em;
	
	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
		// TODO Auto-generated method stub
		return em.createQuery("from Fabricante").getResultList();
	}
	
	@Transactional
	public void excluir(Fabricante fabricanteSelecionado) throws NegocioException{
		try {
			Fabricante fabricante = em.find(Fabricante.class, fabricanteSelecionado.getCodigo());
			em.remove(fabricante);
			em.flush();
			
		} catch (PersistenceException e) {
			// TODO: handle exception
		}
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);
		
		 
	}
}

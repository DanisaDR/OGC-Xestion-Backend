package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Actividade;
import org.springframework.data.domain.Page;

/**
 * <b>IActividadeService.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
public interface IActividadeService {

	public Set<Actividade> getAllActividades();

	public Actividade findByActID(int actID);

	public Page<Actividade> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchActNom);

	public void saveAct(Actividade actividade);

	public void deleteAct(Actividade actividade);

	public void addSociosToAct(Actividade actividade);
}

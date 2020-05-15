package org.redeoza.xestion.service;

import java.util.Date;
import java.util.Set;

import org.redeoza.xestion.model.Actividade;
import org.springframework.data.domain.Page;

public interface IActividadeService {

	Set<Actividade> getAllActividades();

	Actividade findByActID(int actID);

	Page<Actividade> searchAndPagination(int page, int size, String order, boolean ordenationType,
										 String searchActNom, int searchActAport);

	void saveAct(Actividade actividade);

	void deleteAct(Actividade actividade);

	void addSociosToAct(Actividade actividade);
}

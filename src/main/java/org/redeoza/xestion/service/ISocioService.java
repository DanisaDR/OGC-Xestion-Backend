package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Socio;
import org.springframework.data.domain.Page;

public interface ISocioService {

	Set<Socio> getAllSocios();

	Socio getSocioById(Integer socio);

	Page<Socio> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchSocNomComp, String searchSocEnder, String searchSocTfnoFx, String searchSocTfnoMb,
			String searchSocEmail);

	void saveSoc(Socio socio);

	void deleteSoc(Socio socio);

	Socio searchSocByTfnoMb(String socTfnoMb);

	int socActNoActs(boolean socAct);

	boolean existsTfnoMbSoc(String tfnoMb, int socID);

	boolean existsEmailSoc(String socEmail, int socID);
}

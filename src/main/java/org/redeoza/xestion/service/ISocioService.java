package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Socio;
import org.springframework.data.domain.Page;

public interface ISocioService {

	Set<Socio> getAllSocios();

	Socio getSocioById(Integer socio);

	Page<Socio> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchSocNomComp, String searchSocEnder, Integer searchSocTfnoFx, Integer searchSocTfnoMb,
			String searchSocEmail);

	Socio saveSoc(Socio socio);

	void deleteSoc(Socio socio);

	Socio searchSocByTfnoFx(Integer socTfnoFx);

	Socio searchSocByTfnoMb(Integer socTfnoMb);

	int socActNoActs(boolean socAct);

	boolean existsTfnoMbSoc(Integer tfnoMb, int socID);

	boolean existsEmailSoc(String socEmail, int socID);
}

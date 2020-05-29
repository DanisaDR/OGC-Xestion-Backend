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

	Socio saveSoc(Socio socio);

	void deleteSoc(Socio socio);

	int socActNoActs(boolean socAct);

	boolean existsTfnoMbSoc(String tfnoMb, int socID);

	boolean existsEmailSoc(String socEmail, int socID);

	int findLastSoc();
}

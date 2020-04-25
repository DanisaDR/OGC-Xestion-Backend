package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Socio;
import org.springframework.data.domain.Page;

/**
 * <b>ISocioService.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
public interface ISocioService {

	public Set<Socio> getAllSocios();

	public Socio getSocioById(Integer socio);

	public Page<Socio> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchSocNomComp, String searchSocEnder, Integer searchSocTfnoFx, Integer searchSocTfnoMb,
			String searchSocEmail);

	public Socio saveSoc(Socio socio);

	public void deleteSoc(Socio socio);

	public Socio searchSocByTfnoFx(Integer socTfnoFx);

	public Socio searchSocByTfnoMb(Integer socTfnoMb);

	public int socActNoActs(boolean socAct);
}

package org.redeoza.xestion.service;

import org.redeoza.xestion.model.Cota;

import java.util.Set;

/**
 * <b>ICotaService.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
public interface ICotaService {
	int sumCotasByYear(int cotaAnual);

	void saveCota(Cota cota);

	void updateCotaActual(Set<Cota> cotas);
}

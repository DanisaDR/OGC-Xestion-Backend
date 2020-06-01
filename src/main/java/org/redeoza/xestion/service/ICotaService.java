package org.redeoza.xestion.service;

import org.redeoza.xestion.model.Cota;

import java.util.Set;

public interface ICotaService {

	int sumCotasByYear(int cotaAnual);

	Set<Cota> updateCotasBySoc(Set<Cota> cotas);

	void saveCota(Cota cota);

	Set<Cota> allCotasBySoc(int socID);

	void updateCotaActual(Set<Cota> cotas);
}

package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Usuario;
import org.springframework.data.domain.Page;

/**
 * <b>IUserDataService.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
public interface IUsuarioService {

	public Set<Usuario> getAllUsers();

	public Usuario findUserById(int userID);

	public Usuario saveUser(Usuario userData);

	public void deleteUser(Usuario userData);

	public Page<Usuario> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchUsuNom, String searchUsuApe1, String searchUsuApe2, String searchUsuEnder,
			Integer searchUsuTfnoFx, Integer searchUsuTfnoMb);

	public Usuario searchByTfnoMb(Integer usuTfnoMb);

	public Usuario searchByTfnoFx(Integer usuTfnoFx);

	public void establishMonForAct(Usuario newUser);

	public void changeStatusUser(Usuario usuario);

	public boolean existsTfnoMbUser(Integer tfnoMb, int usuID);
}

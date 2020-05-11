package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Usuario;
import org.springframework.data.domain.Page;

public interface IUsuarioService {

	Set<Usuario> getAllUsers();

	Usuario findUserById(int userID);

	Usuario saveUser(Usuario userData);

	void deleteUser(Usuario userData);

	Page<Usuario> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchUsuNom, String searchUsuApe1, String searchUsuApe2, String searchUsuEnder,
			String searchUsuTfnoFx, String searchUsuTfnoMb);

	void establishMonForAct(Usuario newUser);

	void changeStatusUser(Usuario usuario);

	boolean existsTfnoMbUser(String tfnoMb, int usuID);
}

package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.SubMenu;
import com.sistema.colegio.repository.SubMenuRepositoryJPA;
import com.sistema.colegio.service.SubMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class SubMenuServiceImpl implements SubMenuService {


	private final SubMenuRepositoryJPA subMenuRepo;



	@Override
	public List<SubMenu> listarSubMenu() {
		List<SubMenu>subMenu = StreamSupport.stream(
				this.subMenuRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return subMenu;
	}

	@Override
	public void insert(SubMenu subMenu) {
		LocalDate fechaActual = LocalDate.now();
		subMenu.setFechaRegistro(fechaActual);
		subMenuRepo.save(subMenu);
	}

	@Override
	public void update(SubMenu subMenu) {
		LocalDate fechaActual = LocalDate.now();
		subMenu.setFechaRegistro(fechaActual);
		subMenuRepo.save(subMenu);
	}

	@Override
	public void delete(Long subMenuId) {
		subMenuRepo.deleteById(subMenuId);
	}

	@Override
	public SubMenu findById(Long subMenuId) {
		return subMenuRepo.findById(subMenuId).orElse(null);
	}
}

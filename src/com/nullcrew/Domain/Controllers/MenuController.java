package com.nullcrew.Domain.Controllers;

import com.nullcrew.UI.Views.MenuView;

public class MenuController {
	private MenuView menuView;

	public MenuController(MenuView menuView) {
		this.menuView = menuView;
	}

	public MenuView getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

}

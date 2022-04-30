package com.nullcrew.Domain.Controllers;

import com.nullcrew.UI.Views.MenuViewOld;

public class MenuController {
	private MenuViewOld menuView;

	public MenuController(MenuViewOld menuView) {
		this.menuView = menuView;
	}

	public MenuViewOld getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuViewOld menuView) {
		this.menuView = menuView;
	}

}

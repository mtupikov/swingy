package com.swingy.view;

import com.swingy.view.gui.SwingGUI;
import com.swingy.view.shell.ShellGUI;

public class View {
	private SwingGUI swingGui;
	private ShellGUI	shellGui;

	public View(SwingGUI swingGui, ShellGUI shellGui) {
		this.swingGui = swingGui;
		this.shellGui = shellGui;
	}

	public SwingGUI getSwingGui() {
		return swingGui;
	}

	public ShellGUI		getShellGui() {
		return shellGui;
	}
}

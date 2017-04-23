package com.demonhunts.throughit.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.demonhunts.throughit.ThroughIt;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Through It";
        config.width=272;
        config.height=408;
		new LwjglApplication(new ThroughIt(false), config);
	}
}

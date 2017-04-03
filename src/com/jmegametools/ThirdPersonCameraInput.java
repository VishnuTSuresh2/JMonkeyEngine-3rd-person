package com.jmegametools;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;

public abstract class ThirdPersonCameraInput implements AnalogListener{
	public void registerWithInput(InputManager inputManager) {
		inputManager.addMapping("LookLeft", new MouseAxisTrigger(MouseInput.AXIS_X, true),
				new KeyTrigger(KeyInput.KEY_LEFT));

		inputManager.addMapping("LookRight", new MouseAxisTrigger(MouseInput.AXIS_X, false),
				new KeyTrigger(KeyInput.KEY_RIGHT));

		inputManager.addMapping("LookUp", new MouseAxisTrigger(MouseInput.AXIS_Y, false),
				new KeyTrigger(KeyInput.KEY_UP));

		inputManager.addMapping("LookDown", new MouseAxisTrigger(MouseInput.AXIS_Y, true),
				new KeyTrigger(KeyInput.KEY_DOWN));

		inputManager.addListener(this, "LookLeft", "LookRight", "LookUp", "LookDown");
		inputManager.setCursorVisible(false);

	}
	public abstract void onAnalog(String name, float value, float tpf);
}

package com.jmegametools;


import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/**
 *
 * @author Vishnu T Suresh
 */
public class Player extends Character {
	private final ThirdPersonCamera camera;
	private final PlayerInput playerinput;
	// private float speed;
	public Player(BulletAppState bulletAppState,AssetManager assetManager,Node rootNode, Camera cam,InputManager inputManager) {
		super(bulletAppState, assetManager);
		
		ThirdPersonCamera camera;
		PlayerInput playerinput;
		
		camera = new ThirdPersonCamera(cam);
		ThirdPersonCameraInput camerainput = new ThirdPersonCameraInput(){
			@Override
			public void onAnalog(String name, float value, float tpf) {
				if (name.equals("LookLeft")) {
					camera.lookLeft(value);
				} else if (name.equals("LookRight")) {
					camera.lookRight(value);
				} else if (name.equals("LookUp")) {
					camera.lookUp(value);
				} else if (name.equals("LookDown")) {
					camera.lookDown(value);
				}
			}
		};
		playerinput = new PlayerInput(){
			public void onJump(){
				characterphysics.jump();
			}
		};
		camerainput.registerWithInput(inputManager);
		playerinput.registerWithInput(inputManager);
		camera.attachTo(playerNode);
		this.playerinput=playerinput;
		this.camera=camera;
	}
	public void attachTo(Node node){
		node.attachChild(playerNode);
	}
	private Vector3f getWalkDirection(){
		Vector3f walkDirection = new Vector3f();
		Vector3f forwardVector=camera.getForward();
		Vector3f leftVector=camera.getLeft();
		walkDirection.set(0, 0, 0);
		if (playerinput.left) {
			walkDirection.addLocal(leftVector);
		}
		if (playerinput.right) {
			walkDirection.addLocal(leftVector.negate());
		}
		if (playerinput.up) {
			walkDirection.addLocal(forwardVector);
		}
		if (playerinput.down) {
			walkDirection.addLocal(forwardVector.negate());
		}
		return walkDirection;
	}
	public void update() {
		camera.update();
		if (playerinput.left || playerinput.right || playerinput.up || playerinput.down) {
			charactermodel.setRotation(camera.getYaw());
		}
		characterphysics.walk(getWalkDirection());
		super.update();
	}


}
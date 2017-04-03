package com.jmegametools;


import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Vishnu T Suresh
 */
public class Hero implements ActionListener {
	private final CharacterControl player;

	private final Vector3f walkDirection = new Vector3f();
	private boolean left = false, right = false, up = false, down = false;
	private ThirdPersonCamera camera;
	Node heroNode;
	Node meshNode;

	// private float speed;
	public Hero(BulletAppState bulletAppState,Node rootNode, AssetManager assetManager,ThirdPersonCamera camera) {
		this.camera=camera;
		player = new CharacterControl(new CapsuleCollisionShape(1.5f, 6f, 1), 0.05f);
		player.setJumpSpeed(60);
		player.setFallSpeed(60);
		player.setGravity(160);
		player.setPhysicsLocation(new Vector3f(0, 10, 0));
		bulletAppState.getPhysicsSpace().add(player);

		Box box = new Box(1, 1, 1); // create cube shape
		Geometry meshGeom = new Geometry("Box", box); // create cube geometry
														// from the shape
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		
		mat.setColor("Color", ColorRGBA.Blue); // set color of material to blue
		meshGeom.setMaterial(mat); // set the cube's material

		heroNode = new Node("hero");
		meshNode = new Node("heroMesh");
		
		camera.attachTo(heroNode);
		rootNode.attachChild(heroNode);
		heroNode.attachChild(meshNode);
		meshNode.attachChild(meshGeom);
	}
	public void onAction(String binding, boolean isPressed, float tpf) {
		if (binding.equals("Left")) {
			left = isPressed;
		} else if (binding.equals("Right")) {
			right = isPressed;
		} else if (binding.equals("Up")) {
			up = isPressed;
		} else if (binding.equals("Down")) {
			down = isPressed;
		} else if (binding.equals("Jump")) {
			if (isPressed) {
				player.jump();
			}
		}
	}

	public void update() {
		Vector3f forwardVector=camera.getForward();
		Vector3f leftVector=camera.getLeft();
		walkDirection.set(0, 0, 0);
		if (left) {
			walkDirection.addLocal(leftVector);
		}
		if (right) {
			walkDirection.addLocal(leftVector.negate());
		}
		if (up) {
			walkDirection.addLocal(forwardVector);
		}
		if (down) {
			walkDirection.addLocal(forwardVector.negate());
		}
		if (left || right || up || down) {
			meshNode.setLocalRotation(camera.getYaw());
		}
		player.setWalkDirection(walkDirection);
		heroNode.setLocalTranslation(player.getPhysicsLocation());
	}

	public void registerWithInput(InputManager inputManager) {
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(this, "Left");
		inputManager.addListener(this, "Right");
		inputManager.addListener(this, "Up");
		inputManager.addListener(this, "Down");
		inputManager.addListener(this, "Jump");

	}
}
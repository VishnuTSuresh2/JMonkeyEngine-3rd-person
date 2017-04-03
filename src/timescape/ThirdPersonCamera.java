package timescape;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class ThirdPersonCamera implements AnalogListener{
	private final Camera camera;
	private final Node cameraYawNode;
	private final Node cameraPitchNode;
	private final Node cameraNode;
	public ThirdPersonCamera(Camera camera){
		this.camera=camera;
		cameraYawNode = new Node("camerayaw");
		cameraPitchNode = new Node("camerapitch");
		cameraNode = new Node("camera");
		
		cameraYawNode.attachChild(cameraPitchNode);
		cameraPitchNode.attachChild(cameraNode);

		Quaternion q = new Quaternion();
		q.lookAt(cameraPitchNode.getLocalRotation().getRotationColumn(2),
				cameraPitchNode.getLocalRotation().getRotationColumn(1));
		cameraNode.rotate(q).move(cameraPitchNode.getLocalRotation().getRotationColumn(2).mult(-10))
				.move(cameraPitchNode.getLocalRotation().getRotationColumn(1).mult(3));
	}
	public void attachTo(Node node){
		node.attachChild(cameraYawNode);
	}
	public void update() {
		camera.setLocation(cameraNode.getWorldTranslation());
		camera.setRotation(cameraNode.getWorldRotation());
	}
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

	public void onAnalog(String name, float value, float tpf) {
		if (name.equals("LookLeft")) {
			cameraYawNode.rotate(0, 1 * value, 0);
		} else if (name.equals("LookRight")) {
			cameraYawNode.rotate(0, -1 * value, 0);
		} else if (name.equals("LookUp")) {
			cameraPitchNode.rotate(-1 * value, 0, 0);
		} else if (name.equals("LookDown")) {
			cameraPitchNode.rotate(1 * value, 0, 0);
		}
	}
	public Vector3f getForward(){
		Vector3f forwardVector=(cameraYawNode.getWorldRotation().getRotationColumn(2)).multLocal(0.6f);
		return forwardVector;
	}
	public Vector3f getLeft(){
		Vector3f leftVector=(cameraYawNode.getWorldRotation().getRotationColumn(0)).multLocal(0.4f);
		return leftVector;
	}
	public Quaternion getYaw(){
		return cameraYawNode.getLocalRotation();
	}
}

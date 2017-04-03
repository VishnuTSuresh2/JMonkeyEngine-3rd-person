package com.jmegametools;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class ThirdPersonCamera{
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
		cameraNode.rotate(q).move(cameraPitchNode.getLocalRotation().getRotationColumn(2).mult(-20))
				.move(cameraPitchNode.getLocalRotation().getRotationColumn(1).mult(3));
	}
	public void attachTo(Node node){
		node.attachChild(cameraYawNode);
	}
	public void update() {
		camera.setLocation(cameraNode.getWorldTranslation());
		camera.setRotation(cameraNode.getWorldRotation());
	}
	public void lookLeft(float value){
		cameraYawNode.rotate(0, 1 * value, 0);
	}
	public void lookRight(float value){
		cameraYawNode.rotate(0, -1 * value, 0);
	}
	public void lookUp(float value){
		cameraPitchNode.rotate(-1 * value, 0, 0);
	}
	public void lookDown(float value){
		cameraPitchNode.rotate(1 * value, 0, 0);
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

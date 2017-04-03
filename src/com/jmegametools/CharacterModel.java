package com.jmegametools;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class CharacterModel {
	private Node meshNode;
	public CharacterModel(AssetManager assetManager) {
		Box box = new Box(1, 1, 1); // create cube shape
		Geometry meshGeom = new Geometry("Box", box); // create cube geometry
														// from the shape
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		
		mat.setColor("Color", ColorRGBA.Blue); // set color of material to blue
		meshGeom.setMaterial(mat); // set the cube's material
		
		meshNode = new Node("heroMesh");
		meshNode.attachChild(meshGeom);
	}
	public void attachTo(Node node) {
		node.attachChild(meshNode);
	}
	public void setRotation(Quaternion yaw) {
		meshNode.setLocalRotation(yaw);
	}
}

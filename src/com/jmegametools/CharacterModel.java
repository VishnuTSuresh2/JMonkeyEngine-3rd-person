package com.jmegametools;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class CharacterModel {
	private Node meshNode;
	public CharacterModel(AssetManager assetManager) {
		Spatial meshGeom = assetManager.loadModel("me.j3o");
		Material mat = new Material(assetManager,  // Create new material and...
			    "Common/MatDefs/Light/Lighting.j3md"); // ... specify .j3md file to use (illuminated).
		mat.setTexture("DiffuseMap", assetManager.loadTexture("me.png")); // with Unshaded.j3md
		meshNode = new Node("heroMesh");
		meshNode.attachChild(meshGeom);
		meshGeom.setLocalTranslation(0.0f,-4.5f,0.0f);
	}
	public void attachTo(Node node) {
		node.attachChild(meshNode);
	}
	public void lookAt(Vector3f forward) {
		meshNode.lookAt(meshNode.getWorldTranslation().add(forward), meshNode.getLocalRotation().getRotationColumn(1));
	}
}

package com.jmegametools;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;

public class Character {

	protected final Node playerNode;
	protected final CharacterPhysics characterphysics;
	protected final CharacterModel charactermodel;

	public Character(BulletAppState bulletAppState,AssetManager assetManager) {
		CharacterPhysics characterphysics;
		CharacterModel charactermodel;
		playerNode = new Node("hero");
		characterphysics=new CharacterPhysics(bulletAppState);
		charactermodel=new CharacterModel(assetManager);
		charactermodel.attachTo(playerNode);
		this.characterphysics=characterphysics;
		this.charactermodel=charactermodel;
	}
	public void moveTo(float x,float y){
		characterphysics.moveTo(x, y);
	}
	public void attachTo(Node node){
		node.attachChild(playerNode);
	}
	public void update() {
		playerNode.setLocalTranslation(characterphysics.getLocation());
	}
}

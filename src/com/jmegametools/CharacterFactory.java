package com.jmegametools;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class CharacterFactory {
	private final BulletAppState bulletAppState;
	private final Node rootNode;
	private final AssetManager assetManager;
	private final Camera cam;
	private final InputManager inputManager;
	
	public CharacterFactory(BulletAppState bulletAppState,Node rootNode, AssetManager assetManager,Camera cam,InputManager inputManager){
		this.bulletAppState=bulletAppState;
		this.rootNode=rootNode;
		this.assetManager=assetManager;
		this.cam=cam;
		this.inputManager=inputManager;
	}
	public Player createHero(){
		Player hero=new Player(bulletAppState, assetManager, rootNode, cam, inputManager);
		hero.attachTo(rootNode);
		hero.moveTo(10,10);
		return hero;
	}
	public Character createCharacter(){
		Character character=new Character(bulletAppState, assetManager);
		character.attachTo(rootNode);
		return character;
	}
}

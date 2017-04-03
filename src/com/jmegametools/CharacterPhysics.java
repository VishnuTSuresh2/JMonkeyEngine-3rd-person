package com.jmegametools;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;

public class CharacterPhysics {
	private final CharacterControl player;
	public CharacterPhysics(BulletAppState bulletAppState){
		player = new CharacterControl(new CapsuleCollisionShape(1.5f, 6f, 1), 0.05f);
		player.setJumpSpeed(60);
		player.setFallSpeed(60);
		player.setGravity(160);
		player.setPhysicsLocation(new Vector3f(0, 10, 0));
		bulletAppState.getPhysicsSpace().add(player);
	}
	public void jump(){
		player.jump();
	}
	public Vector3f getLocation() {
		return player.getPhysicsLocation();
	}
	public void walk(Vector3f walkDirection){
		player.setWalkDirection(walkDirection);
	}
	public void moveTo(float x,float y){
		player.setPhysicsLocation(new Vector3f(x,player.getPhysicsLocation().y,y));
	}
}

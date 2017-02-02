import java.util.ArrayList;

public class EventManager {
	
	private final String CHAR_COLLISION = "collision";
	private final String CHAR_DEATH = "death";
	private final String CHAR_SPAWN = "spawn";
	private final String INPUT = "input";
	private final String P_SHOOT = "player_shoot";
	private final String E_SHOOT = "enemy_shoot";
	private final String BEGIN_RECORDING = "record";
	private final String START = "start";
	private final String PLAY_REGULAR = "1";
	private final String PLAY_DOUBLE = "2";
	private final String PLAY_HALF = "0";
	
	private static boolean BULLET_AWAY = true;
			
	
	private static ArrayList<Event> queue;
	private ArrayList<Entity> listOfEntities;
	
	private static ArrayList<Event> recordingQueue;
	private ArrayList<Entity> entityStartingState;
	
	private boolean record;
	private Timeline recordTimeline;
	private long recordStartTime;
	private long recordEndTime;
	private boolean playingRecording;
	
	private Timeline timeline;
	private Timeline savedTimeline;
	
	public EventManager(Timeline timeline) {
		queue = new ArrayList<Event>();
		recordingQueue = new ArrayList<Event>();
		playingRecording = false;
		listOfEntities = new ArrayList<Entity>();
		this.timeline = timeline;
		record = false;
	}
	
	public void updateListOfEntities(ArrayList<Entity> list){
		listOfEntities = list;
	}
	
	public synchronized void addToQueue(Event e) {
		queue.add(e);
	}
	
	public boolean handleEvents() {
		Event current = null;
		timeline.updateTimestamp();
		while (!queue.isEmpty() && queue.get(0).getTimestamp() <= timeline.getTimestamp()) {
			current = queue.remove(0);
			if (current != null) {
				if (record) {
					recordingQueue.add(current);
				}
				switch (current.getType()) {
					case START:
						for (Entity enemy : listOfEntities) {
							if (enemy.enemy) {
							int velIndex = 0;
								for (Object obj : enemy.components) {
									if (obj.getClass() == Velocity.class) {
										velIndex = enemy.components.indexOf(obj);
										
										((Velocity)enemy.components.get(velIndex)).set_x_speed(.1);
									}
								}
							}
						}
						BULLET_AWAY = false;
						break;
					case BEGIN_RECORDING:
						record = true;
						entityStartingState = listOfEntities;
						recordingQueue = new ArrayList<Event>();
						recordStartTime = timeline.getCurrentTime();
						break;
					case PLAY_REGULAR:
						playingRecording = true;
						recordEndTime = timeline.getCurrentTime();
						recordingQueue.remove(recordingQueue.size()-1);
						record = false;
						listOfEntities = entityStartingState;
						savedTimeline = timeline;
						
						recordTimeline = new Timeline(recordStartTime);
						timeline = recordTimeline;
						
						queue = recordingQueue;
						break;
					case CHAR_COLLISION:
						Event death = new Event("death");
						death.setID(current.getID());
						death.setPosIndex(current.getPosIndex());
						queue.add(death);
						break;
					case CHAR_DEATH:
						Event spawn = new Event("spawn");
						spawn.setID(current.getID());
						spawn.setPosIndex(current.getPosIndex());
						queue.add(spawn);
						break;
					case CHAR_SPAWN:
						for (Entity ent: listOfEntities) {
							if (ent.EntityID == current.getID()) {
								((Position)ent.components.get(current.getPosIndex())).set_x_position(-50);
								((Position)ent.components.get(current.getPosIndex())).set_y_position(-50);
							}
						}
						break;
					case P_SHOOT:
						double bullet_x = 0;
						double bullet_y = 0;
						for (Entity ent: listOfEntities) {
							if (ent.EntityID == current.getID()) {
								for (Object obj : ent.components) {
									if (obj.getClass() == Position.class) {
										bullet_x = ((Position)obj).get_x_position();
										bullet_y = ((Position)obj).get_y_position();
									}
								}
							}
						}
						Entity bullet = new Entity();
						bullet.bullet = true;
						bullet.addComponents(new MyColor(150, 250, 100));
						bullet.addComponents(new Intent("Add"));
						bullet.addComponents(new Velocity(0, -.8));
						bullet.addComponents(new Size(5, 10));
						bullet.addComponents(new Position(bullet_x, bullet_y));
						
						listOfEntities.add(bullet);
						break;
					case E_SHOOT:
						double enemy_bullet_x = 0;
						double enemy_bullet_y = 0;
						for (Entity ent: listOfEntities) {
							if (ent.EntityID == current.getID()) {
								for (Object obj : ent.components) {
									if (obj.getClass() == Position.class) {
										enemy_bullet_x = ((Position)obj).get_x_position();
										enemy_bullet_y = ((Position)obj).get_y_position();
									}
								}
							}
						}
						Entity enemy_bullet = new Entity();
						enemy_bullet.bullet = true;
						enemy_bullet.addComponents(new MyColor(150, 250, 100));
						enemy_bullet.addComponents(new Intent("Add"));
						enemy_bullet.addComponents(new Velocity(0, 1.4));
						enemy_bullet.addComponents(new Size(5, 10));
						enemy_bullet.addComponents(new Position(enemy_bullet_x, enemy_bullet_y));
						
						listOfEntities.add(enemy_bullet);
						break;
					case INPUT:
						Entity tempEnt = null;
						int entIndex = 0;
						for (Entity ent : listOfEntities) {
							//System.out.println(ent.EntityID + "   " + current.getID());
							if (ent.EntityID == current.getID()) {
								tempEnt = ent;
								entIndex = listOfEntities.indexOf(ent);
							}
						}
						if (tempEnt != null) {
							int velIndex = 0;
							for (Object obj : tempEnt.components) {
								if (obj.getClass() == Velocity.class) {
									velIndex = tempEnt.components.indexOf(obj);
								}
							}
							
							if (current.getInput().equals("LEFT")) {
								((Velocity)tempEnt.components.get(velIndex)).set_x_speed(-.25);
							}
							else if (current.getInput().equals("RIGHT"))
								((Velocity)tempEnt.components.get(velIndex)).set_x_speed(.25);
							else if (current.getInput().equals("UP")) {
								Event playerShoot = new Event("player_shoot");
								playerShoot.setID(current.getID());
								if (BULLET_AWAY == false) {
									BULLET_AWAY = true;
									queue.add(playerShoot);
								}
							} else if (current.getInput().equals("NONE")) {
								((Velocity)tempEnt.components.get(velIndex)).set_x_speed(0);
								((Velocity)tempEnt.components.get(velIndex)).set_y_speed(0);
							}
							
							listOfEntities.set(entIndex, tempEnt);
							//System.out.println(((Velocity)tempEnt.components.get(velIndex)).get_x_speed());
							updateEntities(listOfEntities);
						}
						break;
				}
			}
		}
		updateEntities(listOfEntities);
		
		if (queue.isEmpty())
			return true;
		return false;
	}
	
	private static void updateEntities(ArrayList<Entity> listOfEntities) {
		
		for (Entity ent : listOfEntities) {
			boolean hasVelocity = false;
			double x_vel = 0;
			int velIndex = 0;
			int posIndex = 0;
			double x_pos = 0;
			double y_pos = 0;
			double height = 0;
			double width = 0;
			for(Object obj : ent.components) {
				if (obj.getClass() == Velocity.class) {
					hasVelocity = true;
					x_vel = ((Velocity)obj).get_x_speed();
					velIndex = ent.components.indexOf(obj);
				}
				if (obj.getClass() == Position.class) {
					posIndex = ent.components.indexOf(obj);
					x_pos = ((Position)obj).get_x_position();
					y_pos = ((Position)obj).get_y_position();
				}
				if (obj.getClass() == Size.class) {
					width = ((Size)obj).get_width();
					height = ((Size)obj).get_height();
				}
			}
			/*
			if (ent.enemy && !ent.bullet) {
				double rand = Math.random() * 1000;
				if (rand > 999) {
					Event e_shoot = new Event("enemy_shoot");
					e_shoot.setID(ent.EntityID);
					//e_shoot.setPosIndex(posIndex);
					queue.add(e_shoot);
				}
			}
			*/
			if (hasVelocity) {
				if (!ent.enemy && ent.bullet && y_pos > -5 && y_pos < 0) {
					BULLET_AWAY = false;
					((Velocity)ent.components.get(velIndex)).set_y_speed(0);
					collision(ent, posIndex);
				}
					for (Entity deep_ent : listOfEntities) {
						if (deep_ent.EntityID != ent.EntityID) {
							double d_x_pos = 0;
							double d_y_pos = 0;
							double d_height = 0;
							double d_width = 0;
							int d_pos_index = 0;
							int d_vel_index = 0;
							for (Object obj : deep_ent.components) {
								if (obj.getClass() == Velocity.class) {
									d_vel_index = deep_ent.components.indexOf(obj);
								}
								if (obj.getClass() == Position.class) {
									d_pos_index = deep_ent.components.indexOf(obj);
									d_x_pos = ((Position)obj).get_x_position();
									d_y_pos = ((Position)obj).get_y_position();
								}
								if (obj.getClass() == Size.class) {
									d_width = ((Size)obj).get_width();
									d_height = ((Size)obj).get_height();
								}
							}
							if (!ent.enemy || !deep_ent.enemy) {
								if ((x_pos+(width/2) >= 300 && (x_pos+(width/2) < 305) ) || (x_pos-(width/2) <= 0 && x_pos-(width/2) > -5 ) && x_vel != 0) {
									//if (ent.enemy)
		
									if (ent.enemy) {
										for (Entity enemy : listOfEntities ) {
											if (enemy.enemy) {
												((Velocity)enemy.components.get(velIndex)).set_x_speed(-1*x_vel);
												//Velocity temp_v = ((Velocity)enemy.components.get(velIndex));
												//ScriptManager.loadScript("Scripts/modify_velocity.js");
												//ScriptManager.bindArgument("game_object", temp_v);
												//ScriptManager.executeScript(x_vel);
												//enemy.components.set(velIndex, temp_v);
												double temp_y = ((Position)enemy.components.get(posIndex)).get_y_position();
												((Position)enemy.components.get(posIndex)).set_y_position(temp_y+2);
												/**for(Object obj : enemy.components) {
													if (obj.getClass() == Velocity.class) {
														x_vel = ((Velocity)obj).get_x_speed();
														velIndex = ent.components.indexOf(obj);
														
														((Velocity)enemy.components.get(enemyVelIndex)).set_x_speed(-1*enemy_x_vel);
													}
												}**/
											}
										}
									} else {
										((Velocity)ent.components.get(velIndex)).set_x_speed(-1*x_vel);
									}
								}
								
								if (((x_pos+(width/2)) > (d_x_pos-d_width/2)) && ((y_pos + height/2) == (d_y_pos-d_height/2)) && ((Velocity)(ent.components.get(velIndex))).get_y_speed() != -.25 && ((x_pos-(width/2)) < (d_x_pos+d_width/2))) {
									((Velocity)ent.components.get(velIndex)).set_y_speed(0);
									if (!ent.bullet && deep_ent.enemy)
										collision(ent, posIndex);
								}
								
								if (!ent.bullet && deep_ent.enemy) {
									if ((y_pos-(height/2)) < (d_y_pos + (d_height/2)) && (y_pos+(height/2)) > (d_y_pos -(d_height/2))) {
										if ((x_pos-(width/2)) - (d_x_pos+(d_width/2)) < 2)
											if ((d_x_pos-(d_width/2)) - (x_pos+(width/2)) < 2) {
												collision(ent, posIndex);
											}
									}
								}
								
								if (ent.bullet && deep_ent.enemy) {
									if ((y_pos-(height/2)) < (d_y_pos + (d_height/2)) && (y_pos+(height/2)) > (d_y_pos -(d_height/2))) {
										if ((x_pos-(width/2)) - (d_x_pos+(d_width/2)) < 1)
											if ((d_x_pos-(d_width/2)) - (x_pos+(width/2)) < 1) {
												collision(ent, posIndex);
												((Position)deep_ent.components.get(d_pos_index)).set_x_position(-100);
												((Position)deep_ent.components.get(d_pos_index)).set_y_position(50);
												((Velocity)deep_ent.components.get(d_vel_index)).set_x_speed(0);
												BULLET_AWAY = false;
											}
									}
								}
						
								if (y_pos > 300) {
									collision(ent, posIndex);
									ent.increaseScore();
								}
							}
						}
					}
			}
			ent.update();
		}
		
	}

	private static void collision(Entity ent, int posIndex) {
		Event collision = new Event("collision");
		ScriptManager.bindArgument("game_object", collision);
		ScriptManager.loadScript("Scripts/collision.js");
		
		ScriptManager.executeScript();
		
		collision.setID(ent.EntityID);
		collision.setPosIndex(posIndex);
		queue.add(collision);
		//((Position)ent.components.get(posIndex)).set_x_position(25);
		//((Position)ent.components.get(posIndex)).set_y_position(24);
	}

	public ArrayList<Entity> getUpdatedList() {
		return listOfEntities;
	}
	
	public void setTimeline(Timeline tempTime) {
		timeline = tempTime;
	}
	
	public ArrayList<Event> getQueue() {
		return queue;
	}
	
	public void setQueue(ArrayList<Event> queue) {
		this.queue = queue; 
	}
}

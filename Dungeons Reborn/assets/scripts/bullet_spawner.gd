extends Node
class_name Spawner

@export var bullet:PackedScene

func summon_bullet(damage:float,initial_pos:Vector2,size:float,direction:Vector2,time:float,lifetime:float,type:String,node:Node):
	var new_bullet:Bullet = bullet.instantiate()
	# Get the damage
	new_bullet.damage = damage
	# Get the initial position
	new_bullet.offset = initial_pos
	# Get the argument of the movement function
	new_bullet.direction = direction
	# Get the time where it starts
	new_bullet.time = time
	# Get the type of movement function
	new_bullet.type = type
	# Get the lifetime of the bullet
	new_bullet.kill_timer.wait_time = lifetime
	# Get the size
	new_bullet.scale = Vector2(size,size)
	# Get the summoner
	new_bullet.connection = node
	# Add to the parent
	node.add_child(new_bullet)

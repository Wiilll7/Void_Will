extends Node2D
class_name Room

var hitbox_grid:Dictionary # Vector2i = [nodes]


func test_hitbox(hitbox:Node2D):
	hitbox.get_parent().modulate = Color(1,1,1,1)
	for x in range(int(hitbox.global_position.x - hitbox.hitbox_radius) / 24 - 1,int(hitbox.global_position.x + hitbox.hitbox_radius) / 24 + 2):
		for y in range(int(hitbox.global_position.y - hitbox.hitbox_radius) / 24 - 1,int(hitbox.global_position.y + hitbox.hitbox_radius) / 24 + 2):
			if hitbox_grid.has(Vector2i(x,y)*24):
				for object in hitbox_grid[Vector2i(x,y)*24]:
					if hitbox != object and calculate_hitbox(hitbox,object):
						hitbox.get_parent().modulate = Color(1,0,1,1)
						object.modulate = Color(1,0,1,1)
					else:
						object.modulate = Color(1,1,1,1)

func calculate_hitbox(area_1:Node2D,area_2:Node2D) -> bool:
	var type_1:String = area_1.hitbox_type
	var type_2:String = area_2.hitbox_type
	var dx:float = abs(area_1.global_position.x - area_2.global_position.x)
	var dy:float = abs(area_1.global_position.y - area_2.global_position.y)
	var rad_1:float = area_1.hitbox_radius
	var rad_2:float = area_2.hitbox_radius
	
	if type_1 == "Circle" and type_2 == "Circle":
		return (rad_1 + rad_2)**2 >= (dx**2 + dy**2)
	elif type_1 == "Box" and type_2 == "Circle":
		if dy/dx < 1:
			return (((dy/dx)**2 + 1)**(0.5) * rad_1 + rad_2)**2 >= dx**2 + dy**2
		else:
			return (((dx/dy)**2 + 1)**(0.5) * rad_1 + rad_2)**2 >= dx**2 + dy**2
	elif type_1 == "Circle" and type_2 == "Box":
		if dy/dx < 1:
			return (((dy/dx)**2 + 1)**(0.5) * rad_2 + rad_1)**2 >= dx**2 + dy**2
		else:
			return (((dx/dy)**2 + 1)**(0.5) * rad_2 + rad_1)**2 >= dx**2 + dy**2
	elif type_1 == "Box" and type_2 == "Box":
		return (area_1.global_position.x + rad_1 > area_2.global_position.x - rad_1) and (area_2.global_position.x + rad_2 > area_1.global_position.x - rad_1) and (area_1.global_position.y + rad_1 > area_2.global_position.y - rad_1) and (area_2.global_position.y + rad_2 > area_1.global_position.y - rad_1)
	else:
		return false

func add_object(object:Node2D,has_hitbox:bool):
	if has_hitbox:
		if hitbox_grid.has(Vector2i(object.position)):
			hitbox_grid[Vector2i(object.position)].append(object)
		else: hitbox_grid[Vector2i(object.position)] = [object]
	add_child(object)
func add_entity(entity:Entity):
	if hitbox_grid.has(Vector2i(entity.body.position)):
		hitbox_grid[Vector2i(entity.body.position)].append(entity.body)
	else: hitbox_grid[Vector2i(entity.body.position)] = [entity.body]
	entity.room = self
	add_child(entity)


@export var bullet:PackedScene
func add_bullet(damage:float,initial_pos:Vector2,size:float,direction:Vector2,time:float,lifetime:float,type:String):
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
	# Add to the parent
	if hitbox_grid.has(Vector2i(new_bullet.position)):
		hitbox_grid[Vector2i(new_bullet.position)].append(new_bullet)
	else: hitbox_grid[Vector2i(new_bullet.position)] = [new_bullet]
	
	add_child(new_bullet)

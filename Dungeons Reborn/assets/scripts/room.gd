extends Node2D
class_name Room

var hitbox_grid:Dictionary # Vector2i = [nodes]


func test_hitbox(hitbox:Node2D):
	for x in range(int(hitbox.global_position.x - hitbox.hitbox_radius) / 24,int(hitbox.global_position.x + hitbox.hitbox_radius) / 24):
		for y in range(int(hitbox.global_position.y - hitbox.hitbox_radius) / 24,int(hitbox.global_position.y + hitbox.hitbox_radius) / 24):
			if hitbox_grid.has(Vector2i(x,y)*24):
				for object in hitbox_grid[Vector2i(x,y)*24]:
					if calculate_hitbox(hitbox,object):
						hitbox.modulate = Color(1,0,0,1)
						object.modulate = Color(1,0,0,1)
					else:
						hitbox.modulate = Color(1,1,1,1)
						object.modulate = Color(1,1,1,1)

func calculate_hitbox(area_1:Node2D,area_2:Node2D) -> bool:
	var type_1:String = area_1.hitbox_type
	var type_2:String = area_2.hitbox_type
	var dx:float = abs(area_1.global_position.x - area_2.global_position.x)
	var dy:float = abs(area_1.global_position.y - area_2.global_position.y)
	var rad_1:float = area_1.hitbox_radius
	var rad_2:float = area_2.hitbox_radius
	
	if type_1 == "Circle" and type_2 == "Circle":
		return (rad_1 + rad_2) <= (dx**2 + dy**2)**(0.5)
	elif type_1 == "Box" and type_2 == "Circle":
		if dy/dx < 1:
			return (((dy/dx)**2 + 1)**(0.5) * rad_1 + rad_2)**2 <= dx**2 + dy**2
		else:
			return (((dx/dy)**2 + 1)**(0.5) * rad_1 + rad_2)**2 <= dx**2 + dy**2
	elif type_1 == "Circle" and type_2 == "Box":
		if dy/dx < 1:
			return (((dy/dx)**2 + 1)**(0.5) * rad_1 + rad_2)**2 <= dx**2 + dy**2
		else:
			return (((dx/dy)**2 + 1)**(0.5) * rad_1 + rad_2)**2 <= dx**2 + dy**2
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

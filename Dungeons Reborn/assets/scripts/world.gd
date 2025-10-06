extends Node2D
class_name World

# Prefab
@export var tile_base:PackedScene
@export var enemy:PackedScene
@export var bullet_base:PackedScene

# Active objects
@export var player:Player
@export var solid_tile_list:Node
@export var tile_list:Node
@export var entity_list:Node
@export var player_bullet_list:Node
@export var entity_bullet_list:Node

# Constants // Tests
const tile_size:float = 24
var room_size:Vector2i = Vector2i(100,100)

# Generation
func _ready() -> void:
	create_room("Box",room_size)

func create_room(type:String,size:Vector2i):
	match(type):
		"Box":
			for i in range(size.x):
				for j in range(size.y):
					if j == 0 or j == size.y-1 or i == 0 or i == size.x-1:
						solid_tile_list.add_child(create_tile(1,Vector2(i,j) * tile_size))
					else:
						if i == 6 and j == 6:
							var new_entity:Entity = create_entity("Multishoot",Vector2(i,j) * tile_size)
							new_entity.target = player
							entity_list.add_child(new_entity)
						tile_list.add_child(create_tile(0,Vector2(i,j) * tile_size))
func create_tile(type:int,pos:Vector2) -> Tile:
	var new_tile:Tile = tile_base.instantiate()
	new_tile.frame = type
	new_tile.position = pos
	new_tile.name = str(int(pos.x),"-",int(pos.y))
	return new_tile
func create_entity(type:String,pos:Vector2) -> Entity:
	var new_entity:Entity = enemy.instantiate()
	new_entity.position = pos
	new_entity.type = type
	new_entity.world = self
	return new_entity

# Instantiation
func add_bullet(damage:float,initial_pos:Vector2,size:float,direction:Vector2,time:float,lifetime:float,type:String,entity_bullet:bool):
	var new_bullet:Bullet = bullet_base.instantiate()
	# Get the damage
	new_bullet.damage = damage
	# Get the initial position
	new_bullet.offset = initial_pos
	new_bullet.position = initial_pos
	# Get the argument of the movement function
	new_bullet.direction = direction
	# Get the time where it starts
	new_bullet.time = time
	# Get the type of movement function
	new_bullet.type = type
	# Get the lifetime of the bullet
	new_bullet.kill_timer = lifetime
	# Get the size
	new_bullet.scale = Vector2(size,size)
	
	if entity_bullet:
		entity_bullet_list.add_child(new_bullet)
	else:
		player_bullet_list.add_child(new_bullet)

# Hitbox
func get_tiles_affected(hitbox:Hitbox,detect_range:float) -> Array[Tile]:
	var tiles_affected:Array[Tile]
	var pos:Vector2i = Vector2i(int(hitbox.global_position.x/tile_size),int(hitbox.global_position.y/tile_size)) * tile_size
	
	for x in range(pos.x - (tile_size * ceil(detect_range/tile_size)),pos.x + (tile_size * ceil(detect_range/tile_size)) + 1,tile_size):
		for y in range(pos.y - (tile_size * ceil(detect_range/tile_size)),pos.y + (tile_size * ceil(detect_range/tile_size)) + 1,tile_size):
			var search_name:String = str(solid_tile_list.get_path(),"/",int(x),"-",int(y))
			var tile:Tile = get_node_or_null(search_name)
			if tile != null:
				if hitbox.calculate_hitbox(tile.hitbox): tiles_affected.append(tile)
	
	return tiles_affected

func get_bullets_affected(hitbox:Hitbox,detect_range:float,is_entity:bool) -> Array[Bullet]:
	var bullets_affected:Array[Bullet]
	var target:Node
	
	# Detect if is player of entity projectiles
	if is_entity: target = player_bullet_list
	else: target = entity_bullet_list
	
	for bullet:Bullet in target.get_children():
		if hitbox.global_position.distance_to(bullet.global_position) <= detect_range:
			if hitbox.calculate_hitbox(bullet.hitbox):
				bullets_affected.append(bullet)
	
	return bullets_affected

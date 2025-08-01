extends Node2D
class_name World

@export var tile:PackedScene
@export var enemy:PackedScene
@export var player:Player
const tile_size:float = 24
var room_size:Vector2i = Vector2i(10,10)
@export var room_list:Node2D

func _ready() -> void:
	var main_room:Room = create_room("Box",room_size,Vector2(0,0))
	player.reparent(main_room)
	player.room = main_room
	room_list.add_child(main_room)

func create_room(type:String,size:Vector2i,pos:Vector2) -> Room:
	var new_room:Room = Room.new()
	new_room.position = pos
	match(type):	
		"Box":
			for i in range(size.x):
				for j in range(size.y):
					if j == 0 or j == size.y-1 or i == 0 or i == size.x-1:
						new_room.add_object(create_tile(1,Vector2(i,j) * tile_size),true)
					else:
						if i == 6 and j == 6:
							new_room.add_object(create_entity("Multishoot",Vector2(i,j) * tile_size).body,true)
						new_room.add_object(create_tile(0,Vector2(i,j) * tile_size),false)
	return new_room
func create_tile(type:int,pos:Vector2) -> Node:
	var new_tile:Sprite2D = tile.instantiate()
	new_tile.frame = type
	new_tile.position = pos
	return new_tile
func create_entity(type:String,pos:Vector2) -> Entity:
	var new_entity:Entity = enemy.instantiate()
	new_entity.position = pos
	new_entity.type = type
	return new_entity

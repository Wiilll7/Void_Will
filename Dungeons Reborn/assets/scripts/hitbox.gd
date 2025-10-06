extends Node2D
class_name Hitbox

@export var radius:float
@export var endpoint:Vector2
@export_enum("Circle","Box","Ray") var type:String


func calculate_hitbox(target:Hitbox) -> bool:
	# Use an array
	var obj:Array[Hitbox] = [self,target]
	
	# Safety measures // Ray takes priority
	if (obj[1].type == "Ray"):
		var buffer:Hitbox = obj[1]
		obj[1] = obj[0]
		obj[0] = buffer
	
	# Basic variables to calculations
	var pos:Array[Vector2] = [obj[0].global_position,obj[1].global_position]
	var rad:Array[float] = [0,0]
	var d:Vector2 = abs(pos[0] - pos[1])
	
	# Calculate hitboxes for both objects
	for i in range(2):
		match(obj[i].type):
			"Box":
				d = abs(pos[0] - pos[1])
				if d.x != 0 and d.y/d.x < 1:
					rad[i] = sqrt((d.y/d.x)**2 + 1) * obj[i].radius
				else:
					rad[i] = sqrt((d.x/d.y)**2 + 1) * obj[i].radius
			"Circle":
				rad[i] = obj[i].radius
			"Ray":
				var p:Vector2 = obj[i].endpoint
				var f:Vector2 = pos[1-i] - obj[i].global_position
				pos[i] = obj[i].global_position + max(0, min(1, (f.x * p.x + f.y * p.y) / (p.x * p.x + p.y * p.y))) * p
				rad[i] = 0
				d = abs(pos[0] - pos[1])
	
	return (rad[0] + rad[1])**2 >= d.x**2 + d.y**2

extends Node2D
class_name Bullet

# Behaviour
var offset:Vector2 = Vector2(0,0)
var direction:Vector2
var type:String
var kill_timer:float
var time:float = 0.0:
	set(value):
		time = value
		if value >= kill_timer:
			queue_free()
var damage:float

# Hitbox
@export var hitbox:Hitbox

# Movement of Bullet
func _physics_process(delta: float) -> void:
	time += delta
	position = pattern(delta)

# Functions of movement
func pattern(delta:float) -> Vector2:
	match(type):
		"Orbit":
			# Arg 0 is the radius of the orbit
			return direction * Vector2(cos(time),sin(time)) + offset
		"Throw":
			# Arg 0 is the direction multiplied by velocity
			return position + direction * delta
	return Vector2(0,0)

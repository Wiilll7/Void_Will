extends Node2D
class_name Bullet

var time:float = 0.0
var offset:Vector2 = Vector2(0,0)
var direction:Vector2
var type:String
var connection:Node
@export var kill_timer:Timer

# Hitbox
@export var hitbox_radius:float = 5
@export_enum("Circle","Box") var hitbox_type:String = "Circle"

var damage:float

# Lifetime of bullet
func _ready() -> void:
	position = offset
	$Kill.start()
func _on_kill_timeout() -> void:
	queue_free()

# Movement of Bullet
func _process(delta: float):
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


# When bullet hits
func _on_hitbox_area_entered(area: Area2D) -> void:
	if "hit" in area:
		area.hit(self)

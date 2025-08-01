extends Node2D
class_name BodyComponent

# Speed and movement
var direction:Vector2
var last_direction:Vector2
var momentum:Vector2
@export var speed:float = 200

# Connections and hitbox
@export var main_node:Node
@export var hitbox_radius:float = 5
@export_enum("Circle","Box") var hitbox_type:String = "Circle"

var health:float = 100:
	set(value):
		health = value
		if health <= 0:
			death()
var attack:float = 10

func move(delta:float):
	if direction != Vector2(0,0): last_direction = direction
	if (abs(momentum) - Vector2(100,100) * delta) <= Vector2(0,0):
		momentum = Vector2(0,0)
	else:
		if momentum.x != 0:
			momentum.x -= 100 * delta * abs(momentum.x)/momentum.x
		if momentum.y != 0:
			momentum.y -= 100 * delta * abs(momentum.y)/momentum.y
	return (direction * speed + momentum) * delta

func hit(bullet:Bullet):
	if bullet.connection != self:
		main_node.hit(bullet)
		momentum += bullet.direction.normalized() * 50

func death():
	get_parent().queue_free()

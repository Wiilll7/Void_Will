extends Node
class_name BodyComponent

@export var main_node:Node
var direction:Vector2
var momentum:Vector2
@export var speed:float = 200

var health:float = 100:
	set(value):
		health = value
		if health <= 0:
			death()
var attack:float = 10

func move(delta:float):
	if (abs(momentum) - Vector2(100,100) * delta) <= Vector2(0,0):
		momentum = Vector2(0,0)
	else:
		momentum -= Vector2(100,100) * delta * abs(momentum)/momentum
	return (direction * speed + momentum) * delta

func hit(bullet:Bullet):
	if bullet.connection != self:
		main_node.hit(bullet)
		momentum += bullet.direction.normalized() * 50

func death():
	print("DEATH")
	get_parent().queue_free()

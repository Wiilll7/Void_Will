extends Node2D

@export var body:BodyComponent
@export var spawner:Spawner
@export var target:Node2D

@export var type:String = "Multishoot"


var active:bool = true

func _process(delta: float) -> void:
	if target != null:
		match(type):
			"Multishoot":
				if active:
					active = false
					for j in range(3):
						var target_angle:float = get_angle_to(target.position)
						for i in range(8):
							spawner.summon_bullet(body.attack,Vector2(0,0),1,100*Vector2(cos(PI/4 * i + target_angle),sin(PI/4 * i + target_angle)),0,5,"Throw",body)
						await get_tree().create_timer(0.5).timeout
					await get_tree().create_timer(4).timeout
					active = true
				else:
					if position.distance_to(target.position) <= 100:
						body.direction = (position - target.position + body.direction).normalized()
					else: body.direction = Vector2(0,0)
			"Sniper":
				if active:
					active = false
					for i in range(3):
						spawner.summon_bullet(body.attack,Vector2(0,0),1,500*(target.position-position).normalized(),0,2,"Throw",body)
						await get_tree().create_timer(0.1).timeout
					await get_tree().create_timer(5).timeout
					active = true
				else:
					if position.distance_to(target.position) <= 100:
						visible = false
					else: visible = true
			"Knight":
				if position.distance_to(target.position) <= 100:
					if position.distance_to(target.position) >= 20 and position.distance_to(target.position) <= 50 and active:
						active = false
						var temporary_target:Vector2 = (target.position - position).normalized()
						await get_tree().create_timer(0.5).timeout
						body.direction = temporary_target * 4
						spawner.summon_bullet(body.attack,Vector2(0,0),2,Vector2(0,0),0,1.5,"Throw",body)
						await get_tree().create_timer(0.5).timeout
						active = true
					elif active:
						body.direction = Vector2(0,0)
						"shielded"
				elif active:
					body.direction = (target.position - position).normalized()
	else: body.direction = Vector2(0,0)
	position += body.move(delta)

func hit(bullet:Bullet):
	body.health -= bullet.damage
	bullet.queue_free()

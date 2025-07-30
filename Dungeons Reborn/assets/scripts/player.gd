extends Node2D

@export var body:BodyComponent
@export var spawner:Spawner
@export var defense_cooldown:Timer
@export var dash_cooldown:Timer
@export var attack_cooldown:Timer

var move_lock:bool
var defending:bool
var parry:bool

func _process(delta:float):
	if not move_lock:
		body.direction = Input.get_vector("left","right","up","down")
	
	if Input.is_action_just_pressed("defend") and defense_cooldown.is_stopped():
		body.direction = Vector2(0,0)
		if not defending:
			defending = true
			parry = true
			$Sprite2D.frame = 2 # remove
			await get_tree().create_timer(0.5).timeout
			$Sprite2D.frame = 3 # remove
			parry = false
	
	elif Input.is_action_just_released("defend"):
		defending = false
		move_lock = false
		defense_cooldown.start()
		$Sprite2D.frame = 0 # remove
	
	elif Input.is_action_just_pressed("dash") and dash_cooldown.is_stopped():
		dash_cooldown.start()
		move_lock = true
		body.direction *= 2.5
		$Sprite2D.frame = 4 # remove
		await get_tree().create_timer(0.25).timeout
		$Sprite2D.frame = 4 # remove
		move_lock = false
		
	
	elif Input.is_action_just_pressed("attack") and attack_cooldown.is_stopped():
		attack_cooldown.start()
		spawner.summon_bullet(body.attack,body.direction*10,4,Vector2(0,0),0,delta,"Throw",body)
		$Sprite2D.frame = 1 # remove
	
	position += body.move(delta)
	$Hitbox/ProgressBar.value = body.health


"""
defense: k
	lock movement
	on any time, takes less damage
	on perfect input, negate all damage and destroy bullet
		auto remove shield after parry without cooldown
	small cooldown after releasing the shield
"""

func hit(bullet:Bullet):
	if parry:
		bullet.direction *= -1
		bullet.connection = self
		bullet.damage /= 2
		# Parry cooldown recovery
		parry = false
		defending = false
		defense_cooldown.stop()
	elif defending:
		body.health -= bullet.damage / 2
		bullet.queue_free()
	else:
		body.health -= bullet.damage
		bullet.queue_free()

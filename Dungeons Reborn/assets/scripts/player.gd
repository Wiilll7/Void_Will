extends Node2D
class_name Player

# Connections
@export var body:BodyComponent
@export var hitbox:Hitbox
@export var defense_cooldown:Timer
@export var parry_time:Timer
@export var dash_cooldown:Timer
@export var attack_cooldown:Timer
@export var world:World

# State Machine
var states:Dictionary = {
	"Dash" = false,
	"Attack" = false,
	"Idle" = true,
	"Defend" = false,
	"Parry" = false,
	"Moving" = false
}
var current_state:String = "Idle"
func change_state(new_state:String):
	states[current_state] = false
	states[new_state] = true
	current_state = new_state


# Process
var direction:Vector2
func _physics_process(delta: float) -> void:
	# Input collection
	if current_state == "Moving" or current_state == "Idle":
		direction = Input.get_vector("left","right","up","down")
		$Sprite2D.frame = 0 # remove
	"""
	if Input.is_action_just_pressed("defend") and defense_cooldown.is_stopped():
		direction = Vector2(0,0)
		body.momentum = Vector2(0,0)
		change_state("Defend")
		states["Parry"] = true
		parry_time.start()
		$Sprite2D.frame = 2 # remove
	
	elif Input.is_action_just_released("defend") and defense_cooldown.is_stopped():
		defense_cooldown.start()
		parry_time.stop()
		change_state("Idle")
	
	elif Input.is_action_just_pressed("dash") and dash_cooldown.is_stopped():
		dash_cooldown.start()
		direction = body.last_direction * 3
		change_state("Dash")
		await get_tree().create_timer(0.1).timeout
		body.direction += body.last_direction * 30
		change_state("Idle")
		$Sprite2D.frame = 4 # remove
	
	elif Input.is_action_just_pressed("attack") and attack_cooldown.is_stopped():
		attack_cooldown.start()
		world.add_bullet(body.attack,body.direction*10,4,Vector2(0,0),0,1,"Throw",false)
		direction = Vector2(0,0)
		#body.force += body.direction * 100
		$Sprite2D.frame = 1 # remove
	
	"""
	
	position += body.move(delta,direction,hitbox)
	$ProgressBar.value = body.health

# Think later
"""
func hit(bullet:Bullet):
	if states["Parry"]:
		bullet.direction *= -1
		bullet.connection = self
		bullet.damage /= 2
		# Parry cooldown recovery
		change_state("Idle")
		parry_time.stop()
		states["Parry"] = false
		defense_cooldown.stop()
	elif current_state == "Defend":
		body.health -= bullet.damage / 2
		bullet.queue_free()
	else:
		body.health -= bullet.damage
		bullet.queue_free()

func _on_parry_timeout() -> void:
	states["Parry"] = false
	if current_state == "Defend":
		$Sprite2D.frame = 3 # remove

"""

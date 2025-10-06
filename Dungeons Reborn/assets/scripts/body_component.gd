extends Node2D
class_name BodyComponent

# Connections and hitbox
@export var main_node:Node2D

# Stats
var health:float = 100:
	set(value):
		health = value
		if health <= 0:
			death()
var attack:float = 10

# Speed and movement
var force:Vector2
@export var speed:float = 200
func move(delta:float,direction:Vector2,hitbox:Hitbox) -> Vector2:
	# Calculate resistance and direction input
	if direction == Vector2(0,0):
		var resistance:Vector2 = force.normalized() * delta * 100
		if sign(force.x - resistance.x) != sign(force.x):
			force.x = 0
		else:
			force.x -= resistance.x
		if sign(force.y - resistance.y) != sign(force.y):
			force.y = 0
		else:
			force.y -= resistance.y
	else:
		if abs(force.x + direction.x * speed * delta) > abs(direction.x * speed):
			force.x = direction.x * speed
		else:
			force.x += direction.x * speed * delta * 4
		if abs(force.y + direction.y * speed * delta) > abs(direction.y * speed):
			force.y = direction.y * speed
		else:
			force.y += direction.y * speed * delta * 4
	
	# Collision of tiles
	var tiles_affected:Array[Tile] = main_node.world.get_tiles_affected(hitbox,hitbox.radius)
	if tiles_affected.size() != 0:
		var collision_force:Vector2 = Vector2(0,0)
		for tile in tiles_affected:
			collision_force += (global_position - tile.global_position).normalized() * 100
		force = collision_force
	
	# Collision of bullets
	
	# ---------------------------------------
	# FIX .53 RIGHT NOW
	# --------------------------------------
	var bullets_affected:Array[Bullet] = main_node.world.get_bullets_affected(hitbox,hitbox.radius,false)
	if bullets_affected.size() != 0:
		var collision_force:Vector2 = Vector2(0,0)
		for bullet in bullets_affected:
			collision_force += (global_position - bullet.global_position).normalized() * 50
			hit(bullet)
		force = collision_force
	
	# Return force
	return force * delta

func hit(bullet:Bullet):
	health -= bullet.damage
	bullet.queue_free()

func death():
	get_parent().queue_free()

extends Node2D
var menu: bool = false
@export var chicken: Sprite2D
var ChickenSpeed: float = 200

func _ready() -> void:
		$VBoxContainer.visible=menu
		$PanelContainer.visible=menu

func _process(delta: float) -> void:
	
	# esc button menu input
	
	if Input.is_action_just_released("voltar"):
		menu = not menu
		$VBoxContainer.visible=menu
		$PanelContainer.visible=menu
		
			
			
 	# Chicken movement
	# x1 = 100     x2 = 550, y = 155
	if not menu:
		if chicken.position.x <= 100 or chicken.position.x >= 550:
			ChickenSpeed *= -1
			chicken.flip_h = not chicken.flip_h
		
		chicken.position.x += ChickenSpeed * delta



func _on_button_pressed() -> void:
		menu = false
		$VBoxContainer.visible=menu
		$PanelContainer.visible=menu


func _on_back_to_menu_pressed() -> void:
	get_tree().change_scene_to_file("res://node_2d.tscn")
	
	
func _on_settings_pressed() -> void:
	pass
	

var main = function() {
    let rotationX = 45;
    let rotationY = 20;
    let velocityX = 0;
    let velocityY = 0;
    let lastX = 0;
    let lastY = 0;
    let isDragging = false;
    const $cube = $('.cube');
    let isRotating = false;
    $('.nada').hide();

    $('#hideButton').click(function() {
        $cube.toggle();
        $('.nada').toggle();
    })

    $('.cube').mousedown(function(e) {
        isDragging = true;
        lastX = e.pageX;
        lastY = e.pageY;
    });

    $(window).mousemove(function(e) {

        if (!isDragging) return;

        velocityX = (e.pageX - lastX) * 0.2;
        velocityY = (e.pageY - lastY) * 0.2;
        rotationX += velocityX;
        rotationY += velocityY;

        $cube.css('transform', 'rotateX(' + -rotationY + 'deg)' + ' rotateY(' + rotationX + 'deg)');
        lastX = e.pageX;
        lastY = e.pageY;
    })

    $(window).mouseup(function() {
        isDragging = false;
    });

    $("#girarBtn").click(function(){
        isRotating = !isRotating;
    });

    function step() {
        if (!isDragging) {
            if (isRotating){
                velocityX = 0.5;
            } else {
                velocityX *= 0.95;
            }
            velocityY *= 0.95;
            rotationX += velocityX;
            rotationY += velocityY;
            $cube.css('transform', 'rotateX(' + -rotationY + 'deg)' + ' rotateY(' + rotationX + 'deg)');
        }
        requestAnimationFrame(step);
    }
    
    step();
};

$(document).ready(main);
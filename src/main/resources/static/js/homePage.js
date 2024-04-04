var button = document.getElementById('menu-bar');
var menu = document.querySelector('.option-bar').classList;

button.onclick = function(){
    menu.toggle("invi");
    menu.toggle("vi");
}
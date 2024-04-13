var button = document.getElementById("accord-button");
var table = document.getElementById("accord-table");
i = 220;
button.onclick = function(){
    i = i + 200;
    table.style.maxHeight = i + "px";
    console.log(table.clientHeight);
    console.log(i);
    if(i>parseInt(table.clientHeight)){
        button.style.visibility = "hidden";
    }
}


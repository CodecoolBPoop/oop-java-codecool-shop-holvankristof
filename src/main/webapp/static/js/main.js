function main(){
    let counter = document.querySelector(".badge");
    let addButtons = document.querySelectorAll(".btn");
    for(let button of addButtons){
        button.addEventListener("click", function (evt) {
            var value = parseInt(counter.innerHTML);
            value += 1;
            counter.innerHTML = value.toString();
        })
    }
}

main();

function main(){
    let counter = document.querySelector(".badge");
    let addButtons = document.querySelectorAll(".btn");
    for(let button of addButtons){
        button.addEventListener("click", function () {
            let value = parseInt(counter.innerHTML);
            value += 1;
            counter.innerHTML = value.toString();
            let productId = button.dataset.id;
            updateSession(productId)

        })
    }
    function updateSession(productId) {
        if(sessionStorage.getItem(productId) === null){
            sessionStorage.setItem(productId,"1");

        }else{
            let productCount = sessionStorage.getItem(productId);
            let temp = parseInt(productCount) + 1;
            sessionStorage.setItem(productId,temp);
        }
    };
}

main();

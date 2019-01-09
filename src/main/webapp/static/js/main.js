function main(){
    let counter = document.querySelector(".badge");
    let addButtons = document.querySelectorAll(".btn");
    let cart = document.querySelector(".fa-shopping-cart");

    cart.addEventListener("click",function(){
        for(let i = 0; i <sessionStorage.length;i++){
            let itemName = sessionStorage.key(i);
            let itemCount = sessionStorage.getItem(sessionStorage.key(i));
            let orderItem = document.createElement("input");
            orderItem.type = "hidden";
            orderItem.name = itemName;
            orderItem.value = itemCount;
            cart.appendChild(orderItem);

        }


    });
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

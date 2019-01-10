function main(){
    let counter = document.querySelector(".badge");
    let addButtons = document.querySelectorAll(".btn");
    let cart = document.querySelector(".fa-shopping-cart");
    window.addEventListener("load",function ()  {
        let cartProductCount = 0;
        for(let i = 0; i <sessionStorage.length;i++){
            let productCountTemp = parseInt(sessionStorage.getItem(sessionStorage.key(i)));
            cartProductCount += productCountTemp;


        }
        counter.innerHTML = cartProductCount + "";

    });

   convertSessionDataToForm(cart);

    for(let button of addButtons){
        button.addEventListener("click", function () {
            let value = parseInt(counter.innerHTML);
            value += 1;
            counter.innerHTML = value.toString();
            let productId = button.dataset.id;
            updateSession(productId)


        })
    }

}

main();

function main(){
    let counter = document.querySelector(".badge");
    let addButtons = document.querySelectorAll(".btn");
    let cart = document.querySelector(".fa-shopping-cart");

    cart.addEventListener("click",function(){
        console.log(JSON.stringify(sessionStorage));
        let post = new XMLHttpRequest();
        let url = "/cart";
        let data = new FormData();
        let json = JSON.stringify(sessionStorage)
        post.open("POST",url);
        data.set("products",json);
        console.log(data);
        post.send(data);
        // window.location.replace(url);

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

function updateSession(productId) {


    if(sessionStorage.getItem(productId) === null){
        sessionStorage.setItem(productId,"1");



    }else{
        let productCount = sessionStorage.getItem(productId);
        let temp = parseInt(productCount) + 1;
        sessionStorage.setItem(productId,temp);
    }
};

function deleteItemFromSession(productId){
    if(sessionStorage.getItem(productId) === null){

    }else if(sessionStorage.getItem(productId) === "1"){
        sessionStorage.removeItem(productId);
    }else{
        let productCount = sessionStorage.getItem(productId);
        let temp = parseInt(productCount) -1;
        sessionStorage.setItem(productId,temp)
    }
}

function convertSessionDataToForm(element) {
    element.addEventListener("click",function(){
        for(let i = 0; i <sessionStorage.length;i++){
            let itemName = sessionStorage.key(i);
            let itemCount = sessionStorage.getItem(sessionStorage.key(i));
            let orderItem = document.createElement("input");
            orderItem.type = "hidden";
            orderItem.name = itemName;
            orderItem.value = itemCount;
            element.appendChild(orderItem);

        }


    });

}
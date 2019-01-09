function main(){
    let sumPrices = document.querySelectorAll(".sum_price");
    let productCounts = document.querySelectorAll(".product_count");
    let singlePrices = document.querySelectorAll(".single_price");
    let upArrowIcons = document.querySelectorAll(".fa-arrow-circle-up");
    let downArrowIcons = document.querySelectorAll("fa-arrow-circle-down");
    function round(value, decimals) {
        return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
    }

    window.addEventListener("load",function ()  {
        let totalPrice = 0;

        for(let price of sumPrices){
            let temp = parseFloat(price.textContent);
            totalPrice += temp;
        }
        let totalPriceElement = document.querySelector(".total_price");
        totalPriceElement.innerHTML = ""+ totalPrice + "  ";
    });

    for(let i = 0; i < upArrowIcons.length;i++){
        upArrowIcons[i].addEventListener("click",function () {
            let singlePriceTemp = parseFloat(singlePrices[i].textContent);
            let sumPriceTemp = parseFloat(sumPrices[i].textContent);
            let productCountTemp = parseFloat(productCounts[i].textContent);
            sumPriceTemp += singlePriceTemp;
            productCountTemp += 1;
            sumPrices[i].innerHTML = "" + round(sumPriceTemp,3);
            productCounts[i].innerHTML = "" + productCountTemp;
        })
    }

}

main();
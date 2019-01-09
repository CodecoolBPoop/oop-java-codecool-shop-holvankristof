function main(){
    window.addEventListener("load",function ()  {
        let sumPrices = document.querySelectorAll(".sum_price");
        console.log(sumPrices)
        let totalPrice = 0;

        for(let price of sumPrices){
            let temp = parseFloat(price.textContent);
            totalPrice += temp;
            console.log(totalPrice);
        }
        let totalPriceElement = document.querySelector(".total_price");
        totalPriceElement.innerHTML = ""+ totalPrice + "  ";
    })
}

main();
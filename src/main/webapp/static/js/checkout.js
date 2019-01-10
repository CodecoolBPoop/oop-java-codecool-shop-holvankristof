function main(){
    let paymentButton = document.querySelector(".btn-primary");
    let paymentForm = document.querySelector(".checkout_form");
    paymentButton.addEventListener("click",function () {
        convertSessionDataToForm(paymentForm);

    })

}

main();






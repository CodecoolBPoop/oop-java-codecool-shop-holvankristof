function main(){
    let paymentButton = document.querySelector(".btn");
    let paymentForm = document.querySelector(".checkout_form");
    console.log(paymentButton);
    console.log(paymentForm);
    paymentButton.addEventListener("click",function () {
        convertSessionDataToForm(paymentForm);

    })

}

main();






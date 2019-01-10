function main() {
    let payButton = document.querySelector(".btn");
    payButton.addEventListener("click",function () {
        sessionStorage.clear();
    })
}

main();
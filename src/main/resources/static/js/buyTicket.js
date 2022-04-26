const tickets = document.getElementsByClassName("ticket")

for (let ticket of tickets) {
    ticket.addEventListener('click',pickTicket);
}

document.getElementById("buy-btn").disabled = true;

function pickTicket(){
    let pickedNumber = this.textContent;
    document.getElementById('ticked-picked').textContent = pickedNumber;
    document.getElementById('pickedNumberInput').setAttribute('value',Number(pickedNumber).toString());
    document.getElementById("buy-btn").disabled = false;
}

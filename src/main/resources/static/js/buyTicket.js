const tickets = document.getElementsByClassName("ticket")

for (let ticket of tickets) {
    if (!ticket.classList.contains("boughtTicket")) {
        ticket.addEventListener('click', pickTicket);
    }
}

function pickTicket(){
    let pickedNumber = this.textContent;
    document.getElementById('ticked-picked').textContent = pickedNumber;
    document.getElementById('pickedNumberInput').setAttribute('value',Number(pickedNumber).toString());
}


const tickets = document.getElementsByClassName("ticket")

for (let ticket of tickets) {
    ticket.addEventListener('click',pickTicket);
}

function pickTicket(){
    let pickedNumber = this.textContent;
    document.getElementById('ticked-picked').textContent = pickedNumber;
    document.getElementById('pickedNumberInput').setAttribute('value',Number(pickedNumber).toString());
}


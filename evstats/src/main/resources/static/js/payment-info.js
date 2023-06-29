var tempPrice = parseInt(document.getElementById('ticket-price').textContent);
document.addEventListener('DOMContentLoaded', function() {
    function updatePaymentDetails() {
        var taxAmount = 0;
        var paymentCode = 11;
        var numberOfTickets = parseInt(document.getElementById('tickets').value);

        var totalAmount = tempPrice * numberOfTickets + taxAmount + paymentCode;

        document.getElementById('ticket-price').textContent = 'Rp ' + (tempPrice * numberOfTickets).toLocaleString() + ',00';
        document.getElementById('total').textContent = 'Rp ' + totalAmount.toLocaleString() + ',00';
    }

    // Event handler for ticket change
    function handleTicketChange(event) {
        updatePaymentDetails();
    }

    // Event handler for form submission
    function handleSubmit(event) {
        event.target.reset();
        document.getElementById('tickets').value = 1;
    }

    // Add event listener to the ticket input
    document.getElementById('tickets').addEventListener('input', handleTicketChange);
});

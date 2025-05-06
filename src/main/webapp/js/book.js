// Wait for the DOM to be fully loaded before executing any JavaScript
document.addEventListener('DOMContentLoaded', function() {
    
    // Logo button functionality - redirect to home page when clicked
    const logoButton = document.querySelector('.logo-button');
    // Check if the element was found before attempting to add the listener.
    if (logoButton) {
        logoButton.addEventListener('click', function() {
            window.location.href = 'book.jsp';
        });
    }

    // Borrow button functionality - show confirmation message when clicked
    const borrowButton = document.querySelector('.cta-button');
    // Check if the element was found before attempting to add the listener.
    if (borrowButton) {
        borrowButton.addEventListener('click', function() {
            alert('Book has been added to your borrowing list!');
        });
    }
});
// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Toggle dropdown visibility when status button is clicked
    document.getElementById('statusButton').addEventListener('click', function() {
        // If dropdown is visible, hide it; otherwise, show it
        document.getElementById('statusDropdown').style.display = 
            document.getElementById('statusDropdown').style.display === 'block' ? 'none' : 'block';
    });
    
    // Close dropdown when clicking anywhere outside of it
    window.addEventListener('click', function(event) {
        if (!event.target.matches('.status-button') && !event.target.parentNode.matches('.status-button')) {
            document.getElementById('statusDropdown').style.display = 'none';
        }
    });
});

//Changes the availability status of the book
function changeStatus(status) {
    // Update button text and keep the dropdown arrow SVG
    document.getElementById('statusButton').innerHTML = status + 
        ' <svg width="10" height="6" viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg">' +
        '<path d="M1 1L5 5L9 1" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>' +
        '</svg>';
        
    // Change button color based on status
    // Green for Available, Red for Not Available
    if (status === 'Available') {
        document.getElementById('statusButton').style.backgroundColor = '#34c759';
    } else {
        document.getElementById('statusButton').style.backgroundColor = '#ff3b30';
    }
    
    // Hide the dropdown after selection
    document.getElementById('statusDropdown').style.display = 'none';
}/**
 * 
 */
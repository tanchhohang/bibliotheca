// Function to change book availability status
function changeStatus(status) {
    const statusBadge = document.getElementById('statusBadge');
    
    if (status === 'available') {
        // Set to available (green)
        statusBadge.textContent = 'Available ';
        statusBadge.className = 'status-badge available';
    } else {
        // Set to not available (red)
        statusBadge.textContent = 'Not Available ';
        statusBadge.className = 'status-badge';
    }
    
    // Add back the dropdown arrow
    const arrow = document.createElement('span');
    arrow.className = 'dropdown-arrow';
    statusBadge.appendChild(arrow);
}

// Event listener for clicking on the status badge
document.getElementById('statusBadge').addEventListener('click', function(event) {
    // Toggle dropdown display
    const dropdown = document.getElementById('statusDropdown');
    if (dropdown.style.display === 'block') {
        dropdown.style.display = 'none';
    } else {
        dropdown.style.display = 'block';
    }
    
    // Prevent event bubbling to avoid immediately closing the dropdown
    event.stopPropagation();
});

// Event listener to close dropdown when clicking elsewhere on the page
document.addEventListener('click', function() {
    document.getElementById('statusDropdown').style.display = 'none';
});

// Event listener to prevent dropdown from closing when clicking inside it
document.getElementById('statusDropdown').addEventListener('click', function(event) {
    event.stopPropagation();
});
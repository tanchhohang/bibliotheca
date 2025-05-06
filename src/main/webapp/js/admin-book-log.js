function changeStatus(status) {
    const statusBadge = document.getElementById('statusBadge');
    
    if (status === 'available') {
        // Set to available (green)
        statusBadge.textContent = 'Available ';
        statusBadge.style.backgroundColor = '#2ecc71';
    } else {
        // Set to not available (red)
        statusBadge.textContent = 'Not Available ';
        statusBadge.style.backgroundColor = '#e74c3c';
    }
    
    // Add back the dropdown arrow after changing text content
    const arrow = document.createElement('span');
    arrow.className = 'dropdown-arrow';
    statusBadge.appendChild(arrow);
}

// When document is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Toggle dropdown visibility when clicking the status badge
    document.getElementById('statusBadge').addEventListener('click', function(event) {
        const dropdown = document.getElementById('statusDropdown');
        if (dropdown.style.display === 'block') {
            dropdown.style.display = 'none';
        } else {
            dropdown.style.display = 'block';
        }
        
        // Prevent event bubbling
        event.stopPropagation();
    });
    
    // Close dropdown when clicking elsewhere on the page
    document.addEventListener('click', function() {
        document.getElementById('statusDropdown').style.display = 'none';
    });
    
    // Prevent dropdown from closing when clicking inside it
    document.getElementById('statusDropdown').addEventListener('click', function(event) {
        event.stopPropagation();
    });
});
document.addEventListener('DOMContentLoaded', function () {
  const dropdownButtons = document.querySelectorAll('.dropdown-button');

  dropdownButtons.forEach(button => {
    const dropdownContent = button.nextElementSibling;
    const userId = button.getAttribute('data-user-id');

    button.addEventListener('click', function (e) {
      e.stopPropagation();
      dropdownContent.classList.toggle('show');

      document.querySelectorAll('.dropdown-content.show').forEach(openDropdown => {
        if (openDropdown !== dropdownContent) {
          openDropdown.classList.remove('show');
        }
      });
    });

    dropdownContent.querySelectorAll('.dropdown-option').forEach(option => {
      option.addEventListener('click', function () {
        const selected = option.textContent;
        button.innerHTML = `${selected} <span class="dropdown-arrow">&#9662;</span>`;

        button.classList.remove('admin-role', 'user-role');
        button.classList.add(selected === 'Admin' ? 'admin-role' : 'user-role');

        dropdownContent.classList.remove('show');
      });
    });
  });

  document.addEventListener('click', function () {
    document.querySelectorAll('.dropdown-content.show').forEach(dropdown => {
      dropdown.classList.remove('show');
    });
  });
});
